package edu.zhoutt.mall.controller;

import edu.zhoutt.mall.base.HttpResult;
import edu.zhoutt.mall.pojo.Address;
import edu.zhoutt.mall.pojo.User;
import edu.zhoutt.mall.service.IAddressService;
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
@RequestMapping("/api/address")
@Api(description = "收货地址相关接口")
public class AddressController {

    @Autowired
    private IAddressService addressService;

    @PostMapping("/add")
    @ApiOperation("新增收货地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "收件人名称", required = true),
            @ApiImplicitParam(name = "phone", value = "收件人电话", required = true),
            @ApiImplicitParam(name = "address", value = "收件地址", required = true)
    })
    public HttpResult<Address> add(HttpSession session, String name, String phone, String address) {

        Assert.hasText(name, "收件人名称不能为空");
        Assert.hasText(phone, "收件人电话不能为空");
        Assert.hasText(address, "收件地址不能为空");

        Object user = session.getAttribute("user");

        return HttpResult.success(addressService.add((User) user, name, phone, address));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除单个地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "收货地址id", required = true)
    })
    public HttpResult<Long> delete(@PathVariable("id") Long id) {

        Assert.notNull(id, "收货地址id不能为空");

        return HttpResult.success(addressService.delete(id));
    }


    @PostMapping("/update")
    @ApiOperation("修改收货地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "收货地址id", required = true),
            @ApiImplicitParam(name = "name", value = "收件人名称", required = true),
            @ApiImplicitParam(name = "phone", value = "收件人电话", required = true),
            @ApiImplicitParam(name = "address", value = "收件地址", required = true)
    })
    public HttpResult<Long> update(Long id, String name, String phone, String address) {

        Assert.notNull(id, "收货地址id不能为空");
        Assert.hasText(name, "收件人名称不能为空");
        Assert.hasText(phone, "收件人电话不能为空");
        Assert.hasText(address, "收件地址不能为空");

        return HttpResult.success(addressService.update(id, name, phone, address));
    }

    @GetMapping("/get/list")
    public HttpResult<List<Address>> getListByUserId(HttpSession session) {

        User user = (User) session.getAttribute("user");

        return HttpResult.success(addressService.getListByUserId(user.getId()));
    }
}
