package edu.zhoutt.mall.controller;

import edu.zhoutt.mall.base.HttpResult;
import edu.zhoutt.mall.pojo.Order;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/order")
@Api(description = "订单相关接口")
public class OrderController {

    @PostMapping("/add")
    public HttpResult<Order> add(@RequestParam("car_id") Long carId) {

        System.out.println(carId);

        return HttpResult.success(new Order(1L, 20L, 1, new Date(), new Date()));
    }

}
