package edu.zhoutt.mall.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private Long id;

    private Long userId;

    private String name;

    private String phone;

    private String address;

    private Date createTime;

    private Date updateTime;

}
