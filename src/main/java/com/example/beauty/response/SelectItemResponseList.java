package com.example.beauty.response;

import com.example.beauty.bo.ImgBo;
import com.example.beauty.pojo.Item;
import lombok.Data;

import java.util.List;

/**
 * @Author:Fengxutong
 * @Date:2020/4/18
 * @Description:小冯同学写点注释吧！
 */
@Data
public class SelectItemResponseList {
    private List<SelectItemResponse> itemList;
    private Long total;
}
