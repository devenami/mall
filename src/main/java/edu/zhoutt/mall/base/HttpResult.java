package edu.zhoutt.mall.base;

import edu.zhoutt.mall.configuration.page.Page;
import lombok.Data;

import java.util.List;

@Data
public class HttpResult<T> {

    private T data;

    private int code;

    private String msg;

    private Integer pageNo;

    private Integer pageSize;

    private Long totalElements;

    private Long totalPages;

    private HttpResult(T data, int code, String msg, Integer pageNo, Integer pageSize, Long totalElements, Long totalPages) {
        this.data = data;
        this.code = code;
        this.msg = msg;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    private HttpResult(T data, int code, String msg) {
        this(data, code, msg, null, null, null, null);
    }

    public static <T> HttpResult<T> success(String msg, T data) {
        return new HttpResult<>(data, 1, msg);
    }

    public static <T> HttpResult<T> success(T data) {
        return success(null, data);
    }

    public static <T> HttpResult<T> success() {
        return success(null);
    }

    public static <T> HttpResult<T> error(String msg) {
        return error(0, msg);
    }

    public static <T> HttpResult<T> error(int code, String msg) {
        return new HttpResult<>(null, code, msg);
    }

    public static <T> HttpResult<T> customer(int code, String msg, T data) {
        return customer(code, msg, data, null, null, null, null);
    }

    public static <T> HttpResult<T> customer(int code, String msg, T data, Integer pageNo, Integer pageSize, Long totalElements, Long totalPages) {
        return new HttpResult<>(data, code, null, pageNo, pageSize, totalElements, totalPages);
    }

    @SuppressWarnings("unchecked")
    public static HttpResult page(Object data) {
        if (data instanceof Page) {
            Page page = (Page) data;
            List pageData = page.getData();
            return customer(1, null, pageData, page.getPageNo(), page.getPageSize(), page.getTotalElements(), page.getTotalPages());
        }
        return error("分页异常");
    }

}
