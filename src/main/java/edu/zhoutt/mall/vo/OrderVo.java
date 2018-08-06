package edu.zhoutt.mall.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderVo {

    private Long id;

    private Long userId;

    private String name;

    private String phone;

    private String address;

    private Long addressId;

    private Long productId;

    private Integer number;

    private BigDecimal price;

    private BigDecimal total;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private String productName;

    private String productImage;

    private String description;

    // 是否已下架 0：未下架， 1：已下架
    private Integer isDown;

    // 分类Id
    private Long categoryId;
}
