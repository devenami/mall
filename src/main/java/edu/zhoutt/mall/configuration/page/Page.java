package edu.zhoutt.mall.configuration.page;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页对象
 *
 * @param <T> 泛型
 * @author zhoutt 2018/7/19
 */
public class Page<T> extends ArrayList<T> {

    // 分页数据
    private List<T> data;
    // 当前页码， 从 0 开始
    private Integer pageNo;
    // 当前查询的数量
    private Integer pageSize;
    // 所有的记录数目
    private Long totalElements;
    // 记录的总页数
    private Long totalPages;

    public Page() {

    }

    // 私有化构造器
    public Page(List<T> data, Integer pageNo, Integer pageSize, Long totalElements, Long totalPages) {
        this.data = data;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    public List<T> getData() {
        return data;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public Long getTotalPages() {
        return totalPages;
    }
}
