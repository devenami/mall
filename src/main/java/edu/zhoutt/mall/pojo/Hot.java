package edu.zhoutt.mall.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hot {

    private Long id;

    private Long productId;

    private Long sell;

    private Date createTime;

    private Date updateTime;
}
