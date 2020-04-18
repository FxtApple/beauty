package com.example.beauty.response;

import com.example.beauty.pojo.Product;
import com.example.beauty.request.UpdateProductRequest;
import lombok.Data;

import java.util.List;

/**
 * @Author:Fengxutong
 * @Date:2020/4/18
 * @Description:小冯同学写点注释吧！
 */
@Data
public class SelectProductResponseList {
    private List<Product> selectProductResponseList;

    private Long total;
}
