package com.example.beauty.request;

import lombok.Data;

/**
 * @Author:Fengxutong
 * @Date:2020/4/18
 * @Description:小冯同学写点注释吧！
 */
@Data
public class SelectProductByTypeRequest {
    private Long typeId;
    private Integer pageNumber=1;
    private Integer pageSize=10;
}
