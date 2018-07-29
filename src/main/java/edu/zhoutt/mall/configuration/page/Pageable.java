package edu.zhoutt.mall.configuration.page;

/**
 * 用于查询分页数据的对象
 *
 * @author zhoutt 2018/7/19
 */
public class Pageable {

    private int pageNo;

    private int pageSize;

    private Pageable(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public static Pageable of(int pageNo, int pageSize) {
        return new Pageable(pageNo, pageSize);
    }

    public int getPageNo() {
        return pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }
}
