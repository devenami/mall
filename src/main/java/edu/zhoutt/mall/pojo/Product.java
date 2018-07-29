package edu.zhoutt.mall.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private Long id;

    private String name;

    private String image;

    private BigDecimal price;

    private String description;

    // 是否已下架 0：未下架， 1：已下架
    private Integer isDown;

    // 总数量
    private Long total;

    private Long sell;

    // 分类Id
    private Long categoryId;

    private Date createTime;

    private Date updateTime;
}
