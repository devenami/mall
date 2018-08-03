package edu.zhoutt.mall.configuration.plugin;

import edu.zhoutt.mall.configuration.page.Page;
import edu.zhoutt.mall.configuration.page.Pageable;
import edu.zhoutt.mall.util.ClassUtil;
import org.apache.ibatis.binding.MapperMethod.ParamMap;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Component
@Intercepts({@Signature(type = Executor.class, method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class PageInterceptor implements Interceptor {

    private static final Logger log = LoggerFactory.getLogger(PageInterceptor.class);

    private static int MAPPED_STATEMENT_INDEX = 0;
    private static int PARAMETER_INDEX = 1;
    private static int ROWBOUNDS_INDEX = 2;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        final Object[] queryArgs = invocation.getArgs();

        Object parameter = queryArgs[PARAMETER_INDEX];
        Pageable pageRequest = findPageableObject(parameter);

        if (pageRequest != null) {
            final MappedStatement ms = (MappedStatement) queryArgs[MAPPED_STATEMENT_INDEX];

            BoundSql boundSql = ms.getBoundSql(parameter);
            String sql = boundSql.getSql().trim().replaceAll(";$", "");

            long totalElements = queryTotalElements(sql, ms, boundSql);

            long totalPages = (long) Math.ceil((double) totalElements / (double) pageRequest.getPageSize());

            String limitSql = createLimitSql(sql, pageRequest.getPageNo(), pageRequest.getPageSize());

            queryArgs[ROWBOUNDS_INDEX] = new RowBounds(RowBounds.NO_ROW_OFFSET, RowBounds.NO_ROW_LIMIT);

            queryArgs[MAPPED_STATEMENT_INDEX] = copyFromNewSql(ms, boundSql, limitSql);

            Object ret = invocation.proceed();

            Page<?> page = new Page<>((List<?>) ret,
                    pageRequest.getPageNo(), pageRequest.getPageSize(),
                    totalElements, totalPages);

            return page;
        }

        return invocation.proceed();
    }

    private Object copyFromNewSql(MappedStatement mappedStatement, BoundSql boundSql, String sql) {
        BoundSql newBoundSql = copyFromBoundSql(mappedStatement, boundSql, sql);
        return copyFromMappedStatement(mappedStatement, new BoundSqlSqlSource(newBoundSql));
    }

    private MappedStatement copyFromMappedStatement(MappedStatement ms, BoundSqlSqlSource sqlSource) {
        Builder builder = new Builder(ms.getConfiguration(), ms.getId(), sqlSource, ms.getSqlCommandType());

        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());

        if (ms.getKeyProperties() != null && ms.getKeyProperties().length != 0) {
            StringBuilder keyProperties = new StringBuilder();
            for (String keyProperty : ms.getKeyProperties()) {
                keyProperties.append(keyProperty).append(",");
            }
            keyProperties.delete(keyProperties.length() - 1, keyProperties.length());
            builder.keyProperty(keyProperties.toString());
        }

        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        builder.resultMaps(ms.getResultMaps());
        builder.resultSetType(ms.getResultSetType());
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());

        return builder.build();
    }

    /**
     * 将原 sql 中注入的参数，复制到新sql 中
     */
    private BoundSql copyFromBoundSql(MappedStatement mappedStatement, BoundSql boundSql, String sql) {
        BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(),
                sql, boundSql.getParameterMappings(), boundSql.getParameterObject());
        for (ParameterMapping mapping : boundSql.getParameterMappings()) {
            String prop = mapping.getProperty();
            if (boundSql.hasAdditionalParameter(prop)) {
                newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
            }
        }
        return newBoundSql;
    }

    /**
     * 创建查询语句
     */
    private String createLimitSql(String sql, int pageNo, int pageSize) {

        int offset = pageNo * pageSize;
        return sql + " limit " + offset + "," + pageSize;
    }


    /**
     * 查询所有的总记录数
     */
    private long queryTotalElements(String sql, MappedStatement mappedStatement, BoundSql boundSql) throws SQLException {

        String countSql = new StringBuilder("select count(1) from ( ").append(sql).append(" ) as countTable_1232222 ").toString();

        if (log.isDebugEnabled()) {
            log.debug(countSql);
        }

        Connection connection = null;
        PreparedStatement countStmt = null;
        ResultSet rs = null;
        try {

            connection = mappedStatement.getConfiguration().getEnvironment().getDataSource().getConnection();

            countStmt = connection.prepareStatement(countSql);

            NewBoundSql newBoundSql = ClassUtil.copy(NewBoundSql.class, boundSql);

            BoundSql countBoundSql = new BoundSql(mappedStatement.getConfiguration(), countSql,
                    boundSql.getParameterMappings(), boundSql.getParameterObject());

            Map<String, Object> additionalParameters = newBoundSql.getAdditionalParameters();

            for (Map.Entry<String, Object> entry : additionalParameters.entrySet()) {
                countBoundSql.setAdditionalParameter(entry.getKey(), entry.getValue());
            }

            setParameters(countStmt, mappedStatement, countBoundSql, boundSql.getParameterObject());

            rs = countStmt.executeQuery();

            long totalElements = 0;

            if (rs.next()) {
                totalElements = rs.getLong(1);
            }

            return totalElements;
        } catch (SQLException e) {
            log.error("查询总记录数失败", e);
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (countStmt != null) {
                countStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

    }

    private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql, Object parameterObject) throws SQLException {
        // mappedStatement.getLang().createParameterHandler(mappedStatement, parameterObject, boundSql);
        ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
        parameterHandler.setParameters(ps);
    }

    /**
     * 查找是否存在 Pageable 对象
     *
     * @param params 接口中所有的入参
     * @return Pageable 对象
     */
    private Pageable findPageableObject(Object params) {
        if (params == null) {
            return null;
        }

        // 单个参数， 表现为参数对象
        if (Pageable.class.isAssignableFrom(params.getClass())) {
            return (Pageable) params;
        }

        // 多个参数，表现为ParamMap
        if (params instanceof ParamMap) {
            ParamMap<?> paramMap = (ParamMap<?>) params;
            for (Map.Entry<String, ?> entry : paramMap.entrySet()) {
                Object paramValue = entry.getValue();
                if (paramValue != null && Pageable.class.isAssignableFrom(paramValue.getClass())) {
                    return (Pageable) paramValue;
                }
            }
        }
        return null;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

}
