package edu.zhoutt.mall.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;

    private String username;

    private String password;

    // 0: 普通用户 1：管理员
    private Integer role;

    private Date createTime;

    private Date updateTime;

}
