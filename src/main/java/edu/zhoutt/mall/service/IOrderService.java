package edu.zhoutt.mall.service;

import edu.zhoutt.mall.common.OrderStatus;
import edu.zhoutt.mall.configuration.page.Page;
import edu.zhoutt.mall.configuration.page.Pageable;
import edu.zhoutt.mall.pojo.Order;
import edu.zhoutt.mall.vo.OrderVo;

public interface IOrderService {

    Order add(Long carId, Long addressId);

    Long updateStatus(Long id, OrderStatus orderStatus);

    Page<OrderVo> getPageByUser(Long userId, Pageable pageable);

    Page<OrderVo> getPageByAdmin(Pageable pageable);

    OrderVo getSingle(Long id);
}
