package edu.zhoutt.mall.controller;

import edu.zhoutt.mall.base.HttpResult;
import edu.zhoutt.mall.common.OrderStatus;
import edu.zhoutt.mall.configuration.page.Pageable;
import edu.zhoutt.mall.pojo.Order;
import edu.zhoutt.mall.pojo.User;
import edu.zhoutt.mall.service.IOrderService;
import edu.zhoutt.mall.vo.OrderVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/order")
@Api(description = "订单相关接口")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @PostMapping("/add")
    @ApiOperation("新增订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "car_id", value = "购物车id", required = true),
            @ApiImplicitParam(name = "address_id", value = "收货地址id", required = true)
    })
    public HttpResult<Order> add(@RequestParam("car_id") Long carId, @RequestParam("address_id") Long addressId) {

        Assert.notNull(carId, "购物车id不能为空");
        Assert.notNull(addressId, "收货地址id不能为空");

        return HttpResult.success(orderService.add(carId, addressId));
    }

    @PostMapping("/update/status")
    @ApiOperation("修改订单状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "订单id", required = true),
            @ApiImplicitParam(name = "status", value = "订单状态", required = true)
    })
    public HttpResult<Long> updateStatus(Long id, Integer status) {

        Assert.notNull(id, "id不能为空");

        OrderStatus orderStatus = OrderStatus.fromCode(status);

        Assert.notNull(orderStatus, "订单状态错误");

        return HttpResult.success(orderService.updateStatus(id, orderStatus));
    }


    @GetMapping("/get/page/user")
    @ApiOperation("查询用户的订单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page_no", value = "页码", required = true),
            @ApiImplicitParam(name = "page_size", value = "每页显示的数量", required = true)
    })
    public HttpResult getPageByUser(HttpSession session,
                                    @RequestParam("page_no") Integer pageNo,
                                    @RequestParam("page_size") Integer pageSize) {

        Assert.notNull(pageNo, "页码不能为空");
        Assert.notNull(pageSize, "每页显示的数量不能为空");

        Pageable pageable = Pageable.of(pageNo, pageSize);

        User user = (User) session.getAttribute("user");

        return HttpResult.success(orderService.getPageByUser(user.getId(), pageable));
    }

    @GetMapping("/get/page/admin")
    @ApiOperation("查询用户的订单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page_no", value = "页码", required = true),
            @ApiImplicitParam(name = "page_size", value = "每页显示的数量", required = true)
    })
    public HttpResult getPageByAdmin(HttpSession session,
                                     @RequestParam("page_no") Integer pageNo,
                                     @RequestParam("page_size") Integer pageSize) {

        Assert.notNull(pageNo, "页码不能为空");
        Assert.notNull(pageSize, "每页显示的数量不能为空");

        Pageable pageable = Pageable.of(pageNo, pageSize);

        return HttpResult.success(orderService.getPageByAdmin(pageable));
    }



}
