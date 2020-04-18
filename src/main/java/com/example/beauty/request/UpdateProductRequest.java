package com.example.beauty.request;

import lombok.Data;

/**
 * @Author:Fengxutong
 * @Date:2020/4/18
 * @Description:小冯同学写点注释吧！
 */
@Data
public class UpdateProductRequest {
    private Long id;

    private String productName;

    private Double price;

    private Double salePrice;

    private String productImg;

    private String productImgs;

    private String productDesc;

    private Integer existNumber;

    private Integer saleNumber;

    private Long typeId;
}
