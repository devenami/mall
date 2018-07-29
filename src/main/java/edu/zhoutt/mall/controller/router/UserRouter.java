package edu.zhoutt.mall.controller.router;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@Api(description = "用户路由")
public class UserRouter {

    @GetMapping("/")
    public void root(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/index.html").forward(request, response);
    }

    @GetMapping("/index.html")
    public String index() {
        return "index";
    }

    @GetMapping("/user/user.html")
    public String user$user() {
        return "user/user";
    }

    @GetMapping("/user/register.html")
    public String user$register() {
        return "user/register";
    }

    @GetMapping("/user/login.html")
    public String user$login() {
        return "user/login";
    }

    @GetMapping("/user/address.html")
    public String user$address() {
        return "user/address";
    }

    @GetMapping("/user/car.html")
    public String user$car() {
        return "user/car";
    }

    @GetMapping("/user/order.html")
    public String user$order() {
        return "user/order";
    }
}
