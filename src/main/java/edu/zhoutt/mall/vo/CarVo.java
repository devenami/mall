package edu.zhoutt.mall.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarVo {

    private Long id;

    private Long userId;

    private Long productId;

    private Integer total;

    private Date createTime;

    private Date updateTime;

    private String name;

    private String image;

    private BigDecimal price;

    private String description;

    // 是否已下架 0：未下架， 1：已下架
    private Integer isDown;

    private Long sell;

    private Long productTotal;

    // 分类Id
    private Long categoryId;
}
