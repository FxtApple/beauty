package com.example.beauty.request;

import lombok.Data;

import java.util.List;

/**
 * @Author:Fengxutong
 * @Date:2020/4/18
 * @Description:小冯同学写点注释吧！
 */
@Data
public class UpdateItemRequest {
    private Long id;

    private String itemName;

    private Double price;

    private String itemImg;

    private List<String> imgsUrl;

    private String mainFunction;

    private String targetCrowd;

    private Long typeId;

    private Integer saledNumber;
}
