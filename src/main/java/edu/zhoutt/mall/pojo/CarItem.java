package edu.zhoutt.mall.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarItem {

    private Long id;

    private Long carId;

    private Long productId;

    private Integer total;

    private Date createTime;

    private Date updateTime;

}
