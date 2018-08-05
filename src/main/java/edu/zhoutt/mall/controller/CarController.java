package edu.zhoutt.mall.controller;

import edu.zhoutt.mall.base.HttpResult;
import edu.zhoutt.mall.pojo.Car;
import edu.zhoutt.mall.pojo.User;
import edu.zhoutt.mall.service.ICarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/car")
@Api(description = "购物车相关接口")
public class CarController {

    @Autowired
    private ICarService carService;

    @PostMapping("/add")
    @ApiOperation("新增")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "product_id", value = "产品id", required = true),
            @ApiImplicitParam(name = "total", value = "总数量", required = true)
    })
    public HttpResult<Car> add(HttpSession session, @RequestParam("product_id") Long productId, Integer total) {

        Assert.notNull(productId, "产品id不能为空");
        Assert.notNull(total, "购买数量不能为空");

        User user = (User) session.getAttribute("user");

        return HttpResult.success(carService.add(user.getId(), productId, total));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除购物车")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "购物车id", required = true)
    })
    public HttpResult<Long> deleteById(@PathVariable("id") Long id) {

        Assert.notNull(id, "购物车id不能为空");

        return HttpResult.success(carService.deleteById(id));
    }

    @PostMapping("/update")
    @ApiOperation("修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "购物车id", required = true),
            @ApiImplicitParam(name = "total", value = "总数量", required = true)
    })
    public HttpResult<Long> update(Long id, Integer total) {

        Assert.notNull(id, "购物车id不能为空");
        Assert.notNull(total, "购买数量不能为空");

        return HttpResult.success(carService.update(id, total));
    }

    @GetMapping("/get/list")
    @ApiOperation("查询所有购物车中的内容")
    public HttpResult<List<Car>> getList(HttpSession session) {

        User user = (User) session.getAttribute("user");

        return HttpResult.success(carService.getList(user.getId()));
    }
}