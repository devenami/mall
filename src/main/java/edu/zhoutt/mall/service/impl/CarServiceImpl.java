package edu.zhoutt.mall.service.impl;

import edu.zhoutt.mall.dao.ICarMapper;
import edu.zhoutt.mall.dao.IProductMapper;
import edu.zhoutt.mall.pojo.Car;
import edu.zhoutt.mall.pojo.Product;
import edu.zhoutt.mall.service.ICarService;
import edu.zhoutt.mall.vo.CarVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

@Service
public class CarServiceImpl implements ICarService {

    @Autowired
    private ICarMapper carMapper;

    @Autowired
    private IProductMapper productMapper;

    @Override
    public Car add(Long userId, Long productId, Integer total) {

        Product product = productMapper.findById(productId);
        Assert.notNull(product, "没有查询到产品信息");

        Date currTime = new Date();

        Car car = new Car();
        car.setUserId(userId);
        car.setProductId(productId);
        car.setTotal(total);
        car.setCreateTime(currTime);
        car.setUpdateTime(currTime);

        carMapper.save(car);

        return car;
    }

    @Override
    public Long deleteById(Long id) {

        return carMapper.deleteById(id);
    }


    @Override
    public Long update(Long id, Integer total) {

        Car car = carMapper.findById(id);
        Assert.notNull(car, "信息错误");

        car.setTotal(total);
        car.setUpdateTime(new Date());

        return carMapper.update(car);
    }

    @Override
    public List<CarVo> getList(Long userId) {

        return carMapper.findByUserId(userId);
    }
}
