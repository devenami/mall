package edu.zhoutt.mall.service.impl;

import edu.zhoutt.mall.dao.ICategoryMapper;
import edu.zhoutt.mall.pojo.Category;
import edu.zhoutt.mall.service.ICategoryService;
import edu.zhoutt.mall.vo.CategoryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryMapper categoryMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Category add(Long parentId, String name) {

        if (parentId != 0) {
            Category parentCategory = categoryMapper.findById(parentId);
            Assert.notNull(parentCategory, "没有查询到指定的上层分类");
        }

        // 查询当前分类是否已经存在，存在则跳出
        long count = categoryMapper.countByParentIdAndName(parentId, name);
        Assert.isTrue(count == 0, "待添加的分类已存在");

        Date currTime = new Date();

        Category category = new Category();
        category.setParentId(parentId);
        category.setName(name);
        category.setCreateTime(currTime);
        category.setUpdateTime(currTime);

        categoryMapper.save(category);
        return category;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Long update(Long id, Long parentId, String name) {

        Category category = categoryMapper.findById(id);

        Assert.notNull(category, "没有对应的分类信息");

        category.setName(name);
        category.setParentId(parentId);
        category.setUpdateTime(new Date());

        return categoryMapper.update(category);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Long delete(Long id) {

        Category category = categoryMapper.findById(id);

        Assert.notNull(category, "没有对应的分类信息");

        // 删除子分类
        deleteSubCategory(id);

        return categoryMapper.deleteById(id);
    }


    /**
     * 删除子分类
     */
    private void deleteSubCategory(Long parentId) {

        List<Category> categories = categoryMapper.findByParentId(parentId);

        for (Category category : categories) {
            Long id = category.getId();
            deleteSubCategory(id);
            categoryMapper.deleteById(id);
        }
    }

    @Override
    public List<CategoryVo> get(Long id) {

        List<Category> categories = categoryMapper.findByParentId(id);

        List<CategoryVo> categoryVos = new ArrayList<>();

        for (Category category : categories) {
            CategoryVo categoryVo = new CategoryVo();
            BeanUtils.copyProperties(category, categoryVo);
            categoryVos.add(categoryVo);
        }

        findSubCategory(categoryVos);

        return categoryVos;
    }

    /**
     * 查询所有的子分类
     */
    private void findSubCategory(List<CategoryVo> categoryVos) {
        for (CategoryVo categoryVo : categoryVos) {
            Long id = categoryVo.getId();
            List<Category> categories = categoryMapper.findByParentId(id);
            List<CategoryVo> categoryVoList = new ArrayList<>();
            for (Category category : categories) {
                CategoryVo newCategoryVo = new CategoryVo();
                BeanUtils.copyProperties(category, newCategoryVo);
                categoryVoList.add(newCategoryVo);
            }
            findSubCategory(categoryVoList);
            categoryVo.setSubCategories(categoryVoList);
        }
    }

}
