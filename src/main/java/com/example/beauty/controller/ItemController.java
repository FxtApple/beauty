package com.example.beauty.controller;

import com.example.beauty.common.SzpJsonResult;
import com.example.beauty.request.AddItemRequest;
import com.example.beauty.service.ItemService;
import com.example.beauty.service.impl.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:Fengxutong
 * @Date:2020/4/18
 * @Description:小冯同学写点注释吧！
 */
@RestController
public class ItemController {
    @Autowired
    private ItemService itemService;
    @PostMapping(value = "add/item")
    public SzpJsonResult<Integer> addItem(@RequestBody AddItemRequest addItemRequest) {
        return SzpJsonResult.ok(itemService.addItem(addItemRequest));
    }
}
