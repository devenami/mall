package edu.zhoutt.mall.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Long id;

    private Long addressId;

    private Integer status;

    private Date createTime;

    private Date updateTime;

}
