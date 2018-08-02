package edu.zhoutt.mall.dao;

import edu.zhoutt.mall.configuration.page.Page;
import edu.zhoutt.mall.configuration.page.Pageable;
import edu.zhoutt.mall.pojo.Hot;
import edu.zhoutt.mall.pojo.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface IHotMapper {

    @Options(useGeneratedKeys = true, keyProperty = "hot.id")
    @Insert("INSERT INTO hot (product_id, sell, update_time, create_time) " +
            "VALUES (#{hot.productId}, #{hot.sell}, #{hot.updateTime}, #{hot.createTime})")
    void save(@Param("hot") Hot hot);

    void saveByProducts(@Param("products") List<Product> products);

    Page<Product> findPageByIsDown(@Param("isDown") Integer isDown, @Param("pageable") Pageable pageable);

    @Select("SELECT id, product_id AS productId, sell, update_time AS updateTime, create_time AS createTime " +
            "FROM hot WHERE product_id = #{productId}")
    Hot findByProductId(Long productId);

    @Update("UPDATE hot SET product_id = #{hot.productId}, sell = #{hot.sell}, update_time = #{hot.updateTime} " +
            "WHERE id = #{hot.id}")
    long update(@Param("hot") Hot hot);
}
