package edu.zhoutt.mall.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    private Long id;

    private Long userId;

    private Long productId;

    private Integer total;

    private Date createTime;

    private Date updateTime;

}
