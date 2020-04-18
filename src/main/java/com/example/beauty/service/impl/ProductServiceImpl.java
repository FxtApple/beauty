package com.example.beauty.service.impl;

import com.example.beauty.dao.ProductDao;
import com.example.beauty.pojo.Product;
import com.example.beauty.request.AddProductRequest;
import com.example.beauty.request.SelectProductByTypeRequest;
import com.example.beauty.request.SelectRequest;
import com.example.beauty.request.UpdateProductRequest;
import com.example.beauty.response.SelectProductResponseList;
import com.example.beauty.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by NiuLilu on 2020-04-18 14:57.
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public Integer addProduct(AddProductRequest addProductRequest) {
        Product product = new Product();
        product.setCreateTime(new Date());
        BeanUtils.copyProperties(addProductRequest,product);
        return productDao.addProduct(product);
    }

    @Override
    public Integer deleteProduct(Long productId) {
        return productDao.delectProduct(productId);
    }

    @Override
    public Integer updateProduct(UpdateProductRequest updateProductRequest) {
        Product product = new Product();
        product.setUpdateTime(new Date());
        BeanUtils.copyProperties(updateProductRequest,product);
        return productDao.updateProduct(product);
    }

    @Override
    public SelectProductResponseList selectAllProduct(SelectRequest selectRequest) {
        PageHelper.startPage(selectRequest.getPageNumber(),selectRequest.getPageSize());
        List<Product> productList = productDao.selectProduct();
        Collections.shuffle(productList);
        PageInfo<Product> pageInfo = new PageInfo(productList);
        List<Product> product = pageInfo.getList();
        SelectProductResponseList selectProductResponseList = new SelectProductResponseList();
        selectProductResponseList.setSelectProductResponseList(product);
        selectProductResponseList.setTotal(pageInfo.getTotal());
        return selectProductResponseList;
    }

    @Override
    public SelectProductResponseList selectProductByTypes(SelectProductByTypeRequest selectProductByTypeRequest) {
        PageHelper.startPage(selectProductByTypeRequest.getPageNumber(),selectProductByTypeRequest.getPageSize());
        List<Product> productList = productDao.selectProductByTypeId(selectProductByTypeRequest.getTypeId());
        Collections.shuffle(productList);
        PageInfo<Product> pageInfo = new PageInfo(productList);
        List<Product> product = pageInfo.getList();
        SelectProductResponseList selectProductResponseList = new SelectProductResponseList();
        selectProductResponseList.setSelectProductResponseList(product);
        selectProductResponseList.setTotal(pageInfo.getTotal());
        return selectProductResponseList;
    }
}
