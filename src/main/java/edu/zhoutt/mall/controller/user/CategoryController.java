package edu.zhoutt.mall.controller.user;

import edu.zhoutt.mall.base.HttpResult;
import edu.zhoutt.mall.pojo.Category;
import edu.zhoutt.mall.service.ICategoryService;
import edu.zhoutt.mall.vo.CategoryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@Api(description = "分类相关接口")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @PostMapping("/add")
    @ApiOperation("添加分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parent_id", value = "上一层ID", dataType = "long"),
            @ApiImplicitParam(name = "name", value = "分类名称", dataType = "string", required = true)
    })
    public HttpResult<Category> add(@RequestParam(value = "parent_id", defaultValue = "0") Long parentId,
                                    String name) {

        Assert.notNull(parentId, "上层 Id 不能为空");
        Assert.hasText(name, "分类名称不能为空");

        return HttpResult.success(categoryService.add(parentId, name));
    }

    @PostMapping("/update")
    @ApiOperation("更新分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "long"),
            @ApiImplicitParam(name = "parent_id", value = "上级Id", dataType = "long"),
            @ApiImplicitParam(name = "name", value = "更新的分类名称", dataType = "string", required = true)
    })
    public HttpResult<Long> update(Long id, String name,
                                   @RequestParam(value = "parent_id", defaultValue = "0") Long parentId) {

        Assert.notNull(id, "Id 不能为空");
        Assert.hasText(name, "分类名称不能为空");

        return HttpResult.success(categoryService.update(id, parentId, name));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除分类及其子分类")
    @ApiImplicitParams(@ApiImplicitParam(name = "id", value = "需要删除的分类Id", dataType = "long", required = true))
    public HttpResult<Long> delete(@PathVariable("id") Long id) {

        Assert.notNull(id, "Id 不能为空");

        return HttpResult.success(categoryService.delete(id));
    }

    @GetMapping("/get")
    @ApiOperation("查询指定分类下的所有子分类")
    @ApiImplicitParams(@ApiImplicitParam(name = "id", value = "父级Id, 不传为查所有分类", dataType = "long"))
    public HttpResult<List<CategoryVo>> get(@RequestParam(value = "id", defaultValue = "0") Long id) {
        return HttpResult.success(categoryService.get(id));
    }

}
