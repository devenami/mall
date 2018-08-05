package edu.zhoutt.mall.dao;

import edu.zhoutt.mall.pojo.Car;
import edu.zhoutt.mall.vo.CarVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ICarMapper {

    @Options(useGeneratedKeys = true, keyProperty = "car.id")
    @Insert("INSERT INTO car (`user_id`, `product_id`, `total`, `create_time`, `update_time`) VALUES " +
            "(#{car.userId}, #{car.productId}, #{car.total}, #{car.createTime}, #{car.updateTime})")
    void save(@Param("car") Car car);

    @Delete("DELETE FROM car WHERE `id` = #{id}")
    Long deleteById(Long id);

    @Select("SELECT `id`, `user_id` AS userId, `product_id` AS productId, `total`, " +
            "`create_time` AS createTime, `update_time` AS updateTime FROM car " +
            "WHERE `id` = #{id}")
    Car findById(Long id);

    @Update("UPDATE car SET `total` = #{car.total}, `update_time` = #{car.updateTime} " +
            "WHERE `id` = #{car.id}")
    Long update(@Param("car") Car car);

    List<CarVo> findByUserId(Long userId);
}
