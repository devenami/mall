package edu.zhoutt.mall.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryVo {

    private Long id;

    // 父类别Id, 如果是顶层类，则父类别 Id 为 0
    private Long parentId;

    private String name;

    private Date createTime;

    private Date updateTime;

    private List<CategoryVo> subCategories;

}
