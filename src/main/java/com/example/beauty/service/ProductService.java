package com.example.beauty.service;

import com.example.beauty.request.AddProductRequest;
import com.example.beauty.request.SelectProductByTypeRequest;
import com.example.beauty.request.SelectRequest;
import com.example.beauty.request.UpdateProductRequest;
import com.example.beauty.response.SelectProductResponseList;

/**
 * Created by NiuLilu on 2020-04-18 14:57.
 */
public interface ProductService {
    Integer addProduct(AddProductRequest addProductRequest);

    Integer deleteProduct(Long productId);

    Integer updateProduct(UpdateProductRequest updateProductRequest);

    //根据产品/服务分类查询-----产品
    SelectProductResponseList selectAllProduct(SelectRequest selectRequest);

    //根据产品/服务分类查询-----产品-鼻子
    SelectProductResponseList selectProductByTypes(SelectProductByTypeRequest selectProductByTypeRequest);

    /**
     * //首页展示--不选择-混搭
    SelectProductResponseList selectProduct(SelectProductByTypeRequest selectProductByTypeRequest);
     */
}
