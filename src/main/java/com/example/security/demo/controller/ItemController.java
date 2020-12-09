package com.example.security.demo.controller;

import com.example.security.demo.common.R;
import com.example.security.demo.service.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 权限测试
 *
 * @author knox
 * @date 2020/12/10
 * @since 1.0.0
 */
@RestController
@RequestMapping("/item")
@Api(tags = "商品控制器", value = "ItemController")
public class ItemController extends BaseController {

    @Autowired
    private ItemService itemService;

    /**
     * 关于 hasAuthority与hasRole区别
     * https://blog.csdn.net/qq_26878363/article/details/103632459
     */
    @PreAuthorize("hasAuthority('item:list')")
    // @PreAuthorize("hasRole('ROLE_admin')")
    @GetMapping("/list")
    @ApiOperation(value = "商品列表")
    public R list() {
        return R.ok().data(itemService.list());
    }

    @PreAuthorize("hasAuthority('item:read')")
    @GetMapping("/{id}")
    @ApiOperation(value = "商品明细")
    public R getItem(@PathVariable("id") String id) {
        return R.ok().data(itemService.getById(id));
    }
}
