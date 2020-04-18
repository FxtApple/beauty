package com.example.beauty.dao;

import com.example.beauty.mapper.ProductMapper;
import com.example.beauty.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by NiuLilu on 2020-04-18 14:56.
 */
@Repository
public class ProductDao {
    @Autowired
    private ProductMapper productMapper;

    public Integer addProduct(Product product) {
        return productMapper.insert(product);
    }

    public Integer delectProduct(Long productId) {
        return productMapper.deleteByPrimaryKey(productId);
    }

    public Integer updateProduct(Product product) {
        return productMapper.updateByPrimaryKeySelective(product);
    }

    public List<Product> selectProduct() {
        return productMapper.selectAll();
    }

    public List<Product> selectProductByTypeId(Long typeId) {
        Example example = new Example(Product.class);
        example.createCriteria().andEqualTo("typeId",typeId);
        return productMapper.selectByExample(example);
    }
}
