package edu.zhoutt.mall.controller.user;

import edu.zhoutt.mall.base.HttpResult;
import edu.zhoutt.mall.pojo.User;
import edu.zhoutt.mall.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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

}
