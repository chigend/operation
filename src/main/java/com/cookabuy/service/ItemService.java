package com.cookabuy.service;

import com.cookabuy.entity.service.po.Item;
import com.cookabuy.repository.service.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * 2016/12/7
 */
@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public Page<Item> searchItemsByTileMarketAndCategory(String title,String market,Long categoryId){
        return null;
    }
}
