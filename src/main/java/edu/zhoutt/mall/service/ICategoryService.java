package edu.zhoutt.mall.service;

import edu.zhoutt.mall.pojo.Category;
import edu.zhoutt.mall.vo.CategoryVo;

import java.util.List;

/**
 * 分类
 */
public interface ICategoryService {

    Category add(Long parentId, String name);

    Long update(Long id, Long parentId, String name);

    Long delete(Long id);

    List<CategoryVo> get(Long id);
}
