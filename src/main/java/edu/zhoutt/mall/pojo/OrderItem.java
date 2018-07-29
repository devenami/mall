package edu.zhoutt.mall.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    private Long id;

    private Long orderId;

    private Long productId;

    private Integer total;

    private Date createTime;

    private Date updateTime;

}
