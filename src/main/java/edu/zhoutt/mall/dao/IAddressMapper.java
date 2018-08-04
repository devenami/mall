package edu.zhoutt.mall.dao;

import edu.zhoutt.mall.pojo.Address;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface IAddressMapper {

    @Options(useGeneratedKeys = true, keyProperty = "address.id")
    @Insert("INSERT INTO `address` (`user_id`, `name`, `phone`, `address`, `create_time`, `update_time`) " +
            "VALUES (#{address.userId}, #{address.name}, #{address.phone}, #{address.address}," +
            "#{address.createTime}, #{address.updateTime})")
    void save(@Param("address") Address address);

    @Delete("DELETE FROM address WHERE `id` = #{id}")
    Long deleteById(Long id);

    @Select("SELECT `id`, `user_id` AS userId, `name`, `phone`, `address`, `create_time` AS createTime, `update_time` AS updateTime " +
            "FROM `address` WHERE `id` = #{id}")
    Address findById(Long id);

    @Update("UPDATE `address` SET `user_id` = #{address.userId}, `name` = #{address.name}, " +
            "`phone` = #{address.phone}, `address` = #{address.address}, `update_time` = #{address.updateTime} " +
            "WHERE `id` = #{address.id}")
    Long update(@Param("address") Address address);

    @Select("SELECT `id`, `user_id` AS userId, `name`, `phone`, `address`, `create_time` AS createTime, `update_time` AS updateTime " +
            "FROM address WHERE `user_id` = #{userId}")
    List<Address> findByUserId(Long userId);
}
