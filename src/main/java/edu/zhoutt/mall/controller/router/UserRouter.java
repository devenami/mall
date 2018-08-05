package edu.zhoutt.mall.controller.router;

import edu.zhoutt.mall.pojo.Product;
import edu.zhoutt.mall.service.IProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@Api(description = "用户路由")
public class UserRouter {

    @Autowired
    private IProductService productService;

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

    @GetMapping("/user/detail.html")
    @ApiOperation("跳转产品详情页面")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "产品id", required = true)
    })
    public String user$detail(Long id, Model model) {

        Assert.notNull(id, "产品id不能为空");

        Product product = productService.getSingle(id);
        Assert.notNull(product, "未查询到产品信息");
        model.addAttribute("product", product);

        return "user/detail";
    }
}
