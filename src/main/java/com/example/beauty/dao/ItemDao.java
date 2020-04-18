package com.example.beauty.dao;

import com.example.beauty.mapper.ItemMapper;
import com.example.beauty.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by NiuLilu on 2020-04-18 14:25.
 */
@Repository
public class ItemDao {
    @Autowired
    private ItemMapper itemMapper;

    public Integer addItem(Item item) {
        return itemMapper.insert(item);
    }

    public Integer deleteItem(Long itemId) {
        return itemMapper.deleteByPrimaryKey(itemId);
    }

    public Integer updateItem(Item item) {
        return itemMapper.updateByPrimaryKeySelective(item);
    }

    public List<Item> selectItem() {
        return itemMapper.selectAll();
    }

    public List<Item> selectItemByType(Long typeId) {
        Example example = new Example(Item.class);
        example.createCriteria().andEqualTo("typeId",typeId);
        return itemMapper.selectByExample(example);
    }
}
