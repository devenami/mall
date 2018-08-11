package edu.zhoutt.mall.dao;

import edu.zhoutt.mall.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ICategoryMapper {

    @Options(useGeneratedKeys = true, keyProperty = "category.id")
    @Insert("INSERT INTO category (parent_id, name, create_time, update_time) " +
            "VALUES (#{category.parentId}, #{category.name}, #{category.createTime}, #{category.updateTime})")
    void save(@Param("category") Category category);

    @Select("SELECT id, parent_id AS parentId, name, create_time AS createTime, update_time AS updateTime " +
            "FROM category WHERE id = #{id}")
    Category findById(Long id);

    @Update("UPDATE category SET parent_id = #{category.parentId}, name = #{category.name}, update_time = #{category.updateTime} " +
            "WHERE id = #{category.id}")
    long update(@Param("category") Category category);

    @Delete("DELETE FROM category WHERE id = #{id}")
    long deleteById(Long id);

    @Select("SELECT id, parent_id AS productId, name, create_time AS createTime, update_time AS updateTime " +
            "FROM category WHERE parent_id = #{parentId}")
    List<Category> findByParentId(Long parentId);

    @Select("SELECT COUNT(id) FROM category WHERE parent_id = #{parentId} AND name = #{name}")
    long countByParentIdAndName(@Param("parentId") Long parentId, @Param("name") String name);

    @Select("SELECT id, parent_id AS productId, name, create_time AS createTime, update_time AS updateTime " +
            "FROM category ")
    List<Category> findAll();
}
