package edu.zhoutt.mall.controller.router;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminRouter {

    @GetMapping("/index.html")
    public String admin$index() {
        return "admin/index";
    }

    @GetMapping("/login.html")
    public String admin$login() {
        return "admin/login";
    }

    @GetMapping("/order.html")
    public String admin$order() {
        return "admin/order";
    }

    @GetMapping("/product.html")
    public String admin$product() {
        return "admin/product";
    }

    @GetMapping("/category.html")
    public String admin$category() {
        return "admin/category";
    }
}
