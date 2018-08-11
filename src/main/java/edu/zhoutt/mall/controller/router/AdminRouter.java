package edu.zhoutt.mall.controller.router;

import edu.zhoutt.mall.pojo.Category;
import edu.zhoutt.mall.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminRouter {

    @Autowired
    private ICategoryService categoryService;

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

    @GetMapping("/category_update.html")
    public String admin$categoryUpdate(Long id, Model model) {
        Category category = categoryService.getSingle(id);
        List<Category> all = categoryService.getAll();
        all = all.stream().filter(c -> !c.getId().equals(category.getId())).collect(Collectors.toList());
        model.addAttribute("category", category);
        model.addAttribute("list", all);
        return "admin/category_update";
    }
}
