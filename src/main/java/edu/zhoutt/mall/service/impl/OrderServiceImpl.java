package edu.zhoutt.mall.service.impl;

import edu.zhoutt.mall.common.OrderStatus;
import edu.zhoutt.mall.configuration.page.Page;
import edu.zhoutt.mall.configuration.page.Pageable;
import edu.zhoutt.mall.dao.IAddressMapper;
import edu.zhoutt.mall.dao.ICarMapper;
import edu.zhoutt.mall.dao.IOrderMapper;
import edu.zhoutt.mall.dao.IProductMapper;
import edu.zhoutt.mall.pojo.Address;
import edu.zhoutt.mall.pojo.Car;
import edu.zhoutt.mall.pojo.Order;
import edu.zhoutt.mall.pojo.Product;
import edu.zhoutt.mall.service.IOrderService;
import edu.zhoutt.mall.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private ICarMapper carMapper;

    @Autowired
    private IOrderMapper orderMapper;

    @Autowired
    private IAddressMapper addressMapper;

    @Autowired
    private IProductMapper productMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Order add(Long carId, Long addressId) {

        Car car = carMapper.findById(carId);
        Assert.notNull(car, "购物车信息不存在");

        Address address = addressMapper.findById(addressId);
        Assert.notNull(address, "收货地址不存在");

        Product product = productMapper.findById(car.getProductId());
        Assert.notNull(product, "商品不存在");

        Date currTime = new Date();
        Order order = new Order();
        order.setAddressId(addressId);
        order.setName(address.getName());
        order.setAddress(address.getAddress());
        order.setUserId(address.getUserId());
        order.setPhone(address.getPhone());
        order.setProductId(product.getId());
        order.setNumber(car.getTotal());
        order.setPrice(product.getPrice());
        order.setTotal(product.getPrice().multiply(new BigDecimal(car.getTotal().toString())));
        order.setStatus(OrderStatus.PAIED.getCode());
        order.setUpdateTime(currTime);
        order.setCreateTime(currTime);

        orderMapper.save(order);

        return order;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Long updateStatus(Long id, OrderStatus orderStatus) {

        Order order = orderMapper.findById(id);
        Assert.notNull(order, "订单不存在");

        order.setStatus(orderStatus.getCode());
        order.setUpdateTime(new Date());

        return orderMapper.update(order);
    }

    @Override
    public Page<OrderVo> getPageByUser(Long userId, Pageable pageable) {

        return orderMapper.getPageByUser(userId, pageable);
    }

    @Override
    public Page<OrderVo> getPageByAdmin(Pageable pageable) {
        return orderMapper.getPageByAdmin(pageable);
    }
}
