package com.example.beauty.service;


import com.example.beauty.request.AddItemRequest;
import com.example.beauty.request.SelectItemByTypeRequest;
import com.example.beauty.request.SelectRequest;
import com.example.beauty.request.UpdateItemRequest;
import com.example.beauty.response.SelectItemResponseList;

/**
 * @Author:Fengxutong
 * @Date:2020/4/18
 * @Description:小冯同学写点注释吧！
 */
public interface ItemService {
    Integer addItem(AddItemRequest addItemRequest);

    Integer deleteItem(Long productId);

    Integer updateItem(UpdateItemRequest updateItemRequest);

    //根据产品/服务分类查询-----项目
    SelectItemResponseList selectAllItem(SelectRequest selectRequest);

    //根据产品/服务分类查询-----项目-鼻子
    SelectItemResponseList selectItemByTypes(SelectItemByTypeRequest selectItemByTypeRequest);

    /**
     * //首页展示--不选择-混搭
     SelectItemResponseList selectItem(SelectItemByTypeRequest selectItemByTypeRequest);
     */
}
