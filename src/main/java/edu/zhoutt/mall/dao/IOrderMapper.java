package edu.zhoutt.mall.dao;

import edu.zhoutt.mall.configuration.page.Page;
import edu.zhoutt.mall.configuration.page.Pageable;
import edu.zhoutt.mall.pojo.Order;
import edu.zhoutt.mall.vo.OrderVo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface IOrderMapper {

    @Options(useGeneratedKeys = true, keyProperty = "order.id")
    @Insert("INSERT INTO `order` (`user_id`, `name`, `phone`, `address`, `address_id`, `product_id`, `number`, `price`, `total`, `status`, `create_time`, `update_time`) VALUES " +
            "(#{order.userId}, #{order.name}, #{order.phone}, #{order.address}, #{order.addressId}, #{order.productId}, #{order.number}, #{order.price}," +
            "#{order.total}, #{order.status}, #{order.createTime}, #{order.updateTime})")
    void save(@Param("order") Order order);

    @Select("SELECT `id`, `user_id` AS userId, `name`, `phone`, `address`, `address_id` AS addressId, `product_id` AS productId," +
            " `number`, `price`, `total`, `status`, `create_time` AS createTime, `update_time` AS updateTime FROM `order` " +
            "WHERE id = #{id}")
    Order findById(Long id);

    @Update("UPDATE `order` SET `user_id` = #{order.userId}, `name` = #{order.name}, `phone` = #{order.phone}, `address` = #{order.address}," +
            " `address_id` = #{order.addressId}, `product_id` = #{order.productId}, `number` = #{order.number}, `price` = #{order.price}," +
            " `total` = #{order.total}, `status` = #{order.status}, `update_time` = #{order.updateTime} " +
            "WHERE `id` = #{order.id}")
    Long update(@Param("order") Order order);

    Page<OrderVo> getPageByUser(@Param("userId") Long userId, @Param("pageable") Pageable pageable);

    Page<OrderVo> getPageByAdmin(@Param("pageable") Pageable pageable);

    OrderVo findVoById(Long id);
}
