package edu.zhoutt.mall.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    private Long id;

    // 父类别Id, 如果是顶层类，则父类别 Id 为 0
    private Long parentId;

    private String name;

    private Date createTime;

    private Date updateTime;

}
