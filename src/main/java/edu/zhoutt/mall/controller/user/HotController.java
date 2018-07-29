package edu.zhoutt.mall.controller.user;

import edu.zhoutt.mall.base.HttpResult;
import edu.zhoutt.mall.configuration.page.Pageable;
import edu.zhoutt.mall.service.IHotService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hot")
@Api(description = "热门商品相关接口")
public class HotController {

    @Autowired
    private IHotService hotService;

    @GetMapping("/get/page/{pageNo}/{pageSize}")
    @ApiOperation("查询当前热门商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码,从0开始", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页显示的数量", required = true)
    })
    public HttpResult getPage(@PathVariable("pageNo") Integer pageNo,
                              @PathVariable("pageSize") Integer pageSize) {

        Assert.notNull(pageNo, "页码不能为空");
        Assert.notNull(pageSize, "每页显示的数量不能为空");

        Pageable pageable = Pageable.of(pageNo, pageSize);

        return HttpResult.page(hotService.getPage(pageable));
    }

}
