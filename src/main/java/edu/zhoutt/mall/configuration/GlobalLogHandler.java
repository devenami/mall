package edu.zhoutt.mall.configuration;

import edu.zhoutt.mall.util.JsonUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Aspect
public class GlobalLogHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalLogHandler.class);

    @Around("execution(public * edu.zhoutt.mall.controller..*.*(..) )")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        log.info("============== entry ===============");
        log.info("REQUEST_URL: {}", request.getRequestURI());
        log.info("REQUEST_METHOD: {}", request.getMethod());

        log.info("METHOD_NAME: {}", joinPoint.getSignature().getDeclaringType() + "#" + joinPoint.getSignature().getName());

        log.info("REQUEST_PARAMETERS ( ");
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (String key : parameterMap.keySet()) {
            StringBuilder sb = new StringBuilder(key).append(" :{ ");
            String[] values = parameterMap.get(key);
            for (String value : values) {
                sb.append(value);
            }
            sb.append(" }");
            log.info(sb.toString());
        }
        log.info(" )");

        Object obj = joinPoint.proceed();

        log.info("RETURN_VALUE: {}", JsonUtil.toJson(obj));
        log.info("============== exit ===============");

        return obj;
    }

}
