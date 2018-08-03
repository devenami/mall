package edu.zhoutt.mall.configuration.page;

import org.springframework.util.Assert;

/**
 * 用于查询分页数据的对象
 *
 * @author zhoutt 2018/7/19
 */
public class Pageable {

    private Integer pageNo;

    private Integer pageSize;

    private Pageable(Integer pageNo, Integer pageSize) {

        Assert.notNull(pageNo, "pageNo 不能为空");
        Assert.notNull(pageSize, "pageSize 不能为空");

        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public static Pageable of(Integer pageNo, Integer pageSize) {
        return new Pageable(pageNo, pageSize);
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }
}
