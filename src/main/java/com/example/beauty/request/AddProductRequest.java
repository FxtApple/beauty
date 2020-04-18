package com.example.beauty.request;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

/**
 * @Author:Fengxutong
 * @Date:2020/4/18
 * @Description:小冯同学写点注释吧！
 */
@Data
public class AddProductRequest {
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
