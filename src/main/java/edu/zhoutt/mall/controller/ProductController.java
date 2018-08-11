package edu.zhoutt.mall.controller;

import edu.zhoutt.mall.base.HttpResult;
import edu.zhoutt.mall.common.IsDown;
import edu.zhoutt.mall.configuration.page.Pageable;
import edu.zhoutt.mall.pojo.Product;
import edu.zhoutt.mall.service.IProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/product")
@Api(description = "产品相关接口")
public class ProductController {

    @Autowired
    private IProductService productService;

    @PostMapping("/add")
    @ApiOperation("新增商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "商品名称", required = true),
            @ApiImplicitParam(name = "image", value = "商品图片", required = true),
            @ApiImplicitParam(name = "price", value = "商品价格", required = true),
            @ApiImplicitParam(name = "description", value = "商品描述"),
            @ApiImplicitParam(name = "total", value = "商品总数量", required = true),
            @ApiImplicitParam(name = "category_id", value = "商品所属分类Id", required = true)
    })
    public HttpResult<Product> add(String name, String image, BigDecimal price, String description,
                                   Long total, @RequestParam("category_id") Long categoryId) {

        Assert.hasText(name, "商品名称不能为空");
        Assert.hasText(image, "商品图片不能为空");
        Assert.notNull(price, "商品价格不能为空");
        Assert.notNull(total, "商品数量不能为空");
        Assert.notNull(categoryId, "商品所属分类不能为空");

        return HttpResult.success(productService.add(name, image, price, description, total, categoryId));
    }


    @PostMapping("/update")
    @ApiOperation("新增商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品id", required = true),
            @ApiImplicitParam(name = "name", value = "商品名称", required = true),
            @ApiImplicitParam(name = "price", value = "商品价格", required = true),
            @ApiImplicitParam(name = "description", value = "商品描述"),
            @ApiImplicitParam(name = "total", value = "商品总数量", required = true),
            @ApiImplicitParam(name = "category_id", value = "商品所属分类Id", required = true)
    })
    public HttpResult<Long> update(Long id, String name, BigDecimal price, String description,
                                   Long total, @RequestParam("category_id") Long categoryId) {

        Assert.notNull(id, "主键不能为空");
        Assert.hasText(name, "商品名称不能为空");
        Assert.notNull(price, "商品价格不能为空");
        Assert.notNull(total, "商品数量不能为空");
        Assert.notNull(categoryId, "商品所属分类不能为空");

        return HttpResult.success(productService.update(id, name, price, description, total, categoryId));
    }

    @PostMapping("/is_down/{id}/{isDown}")
    @ApiOperation("修改商品售卖状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品Id", required = true),
            @ApiImplicitParam(name = "isDown", value = "0:正常, 1:下架", required = true)
    })
    public HttpResult<Long> changeIsDown(@PathVariable("id") Long id, @PathVariable("isDown") Integer isDown) {

        Assert.notNull(id, "商品Id不能为空");

        IsDown isDownEnum = IsDown.fromCode(isDown);
        Assert.notNull(isDown, "售卖状态不正确");

        return HttpResult.success(productService.changeIsDown(id, isDownEnum));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品Id", required = true)
    })
    public HttpResult<Long> delete(@PathVariable("id") Long id) {

        Assert.notNull(id, "商品Id不能为空");

        return HttpResult.success(productService.delete(id));
    }

    @GetMapping("/get/single/{id}")
    @ApiOperation("根据商品Id查询单个商品")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id", value = "商品id", required = true)
    )
    public HttpResult<Product> getSingle(@PathVariable("id") Long id) {

        Assert.notNull(id, "商品id不能为空");

        return HttpResult.success(productService.getSingle(id));
    }

    @GetMapping("/get/category/keyword")
    @ApiOperation("根据分类id或关键词分页查询商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "categoryId", value = "分类id"),
            @ApiImplicitParam(name = "keyword", value = "关键词"),
            @ApiImplicitParam(name = "pageNo", value = "页码, 从0开始", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页显示的数量", required = true)
    })
    public HttpResult getProductListByPage(@RequestParam(value = "category_id", required = false) Long categoryId,
                                           @RequestParam(value = "keyword", required = false) String keyword,
                                           @RequestParam("page_no") Integer pageNo,
                                           @RequestParam("page_size") Integer pageSize) {

        Assert.notNull(pageNo, "当前页码不能为空");
        Assert.notNull(pageSize, "每页显示的数量不能为空");

        Pageable pageable = Pageable.of(pageNo, pageSize);

        return HttpResult.page(productService.getProductListByPage(categoryId, keyword, pageable));
    }

    @GetMapping("/get/all")
    @ApiOperation("获取所有的产品")
    public HttpResult<List<Product>> getAll() {
        return HttpResult.success(productService.getAll());
    }

    @PostMapping("/file/upload")
    @ApiOperation("产品文件上传")
    public HttpResult fileUpload(MultipartFile file) {
        return HttpResult.success(productService.fileUpload(file));
    }


}
