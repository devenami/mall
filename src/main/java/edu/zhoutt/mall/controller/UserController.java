package edu.zhoutt.mall.controller;

import edu.zhoutt.mall.base.HttpResult;
import edu.zhoutt.mall.common.UserRole;
import edu.zhoutt.mall.pojo.User;
import edu.zhoutt.mall.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@Api(description = "用户相关的接口")
@RequestMapping("/api/user")
public class UserController {

    private IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }


    @GetMapping("/check/{username}")
    @ApiOperation("检查用户名是否存在")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", dataType = "string", required = true)
    })
    public HttpResult<Integer> checkUsername(@PathVariable("username") String username) {
        return HttpResult.success(userService.checkUsername(username));
    }

    @PostMapping("/register")
    @ApiOperation("用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", dataType = "string", required = true),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "string", required = true),
            @ApiImplicitParam(name = "role", value = "用户角色:0普通用户,1管理员", dataType = "int", required = true)
    })
    public HttpResult<User> register(String username, String password, Integer role) {

        Assert.hasText(username, "用户名不能为空");
        Assert.hasText(password, "密码不能为空");
        Assert.notNull(UserRole.fromCode(role), "身份信息不能为空");

        return HttpResult.success(userService.register(username, password, role));
    }

    @PostMapping("/login")
    @ApiOperation("用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", dataType = "string", required = true),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "string", required = true)
    })
    public HttpResult<User> login(String username, String password, HttpSession session) {
        return HttpResult.success(userService.login(username, password, session));
    }

    @PostMapping("/logout")
    @ApiOperation("用户退出")
    public HttpResult<Object> logout(HttpSession session) {

        session.invalidate();

        return HttpResult.success();
    }


    @PostMapping("/update/username")
    @ApiOperation("修改用户名")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true),
            @ApiImplicitParam(name = "role", value = "0:普通用户，1:管理员", required = true)
    })
    public HttpResult<Long> updateUsername(HttpSession session, String username, Integer role) {

        Assert.hasText(username, "修改的名称不能为空");

        UserRole userRole = UserRole.fromCode(role);

        Assert.notNull(userRole, "用户角色不能为空");

        Object userObj = session.getAttribute("user");
        if (userObj == null) {
            return HttpResult.error("未登录");
        }

        return HttpResult.success(userService.updateUsername(session, (User) userObj, username, userRole));
    }

    @PostMapping("/update/password")
    @ApiOperation("修改用户密码")
    public HttpResult<Long> updatePassword(HttpSession session, String password, Integer role) {

        Assert.hasText(password, "修改的密码不能为空");

        UserRole userRole = UserRole.fromCode(role);

        Assert.notNull(userRole, "用户角色不能为空");

        Object userObj = session.getAttribute("user");
        if (userObj == null) {
            return HttpResult.error("未登录");
        }

        return HttpResult.success(userService.updatePassword((User) userObj, password, userRole));
    }

    @PostMapping("/update/password/reset")
    @ApiOperation("超级管理员重置密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", dataType = "string", required = true),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "string", required = true),
            @ApiImplicitParam(name = "role", value = "用户角色:0普通用户,1管理员", dataType = "int", required = true)
    })
    public HttpResult<Long> resetPassword(HttpSession session, String username, String password, Integer role) {

        User user = (User) session.getAttribute("user");

        Assert.isTrue(UserRole.fromCode(user.getRole()).equals(UserRole.ROOT), "越权");
        Assert.hasText(username, "用户名不能为空");
        Assert.hasText(password, "密码不能为空");
        Assert.notNull(UserRole.fromCode(role), "身份信息不能为空");

        return HttpResult.success(userService.resetPassword(username, password, role));
    }

    @GetMapping("/get/current")
    @ApiOperation("获取当前登录的用户信息")
    public HttpResult<Object> getCurrentUser(HttpSession session) {
        return HttpResult.success(session.getAttribute("user"));
    }
}
