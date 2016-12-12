package com.cookabuy.controller;

import com.cookabuy.entity.service.po.Item;
import com.cookabuy.repository.service.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 2016/12/6
 */
@RestController
@Slf4j
@RequestMapping("/api")
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;
//    @RequestMapping("/search")
//    public List<Item> searchItem(String keyword, String type){
//        switch (type){
//            case SearchType.ITEM:
//                return itemRepository.find
//        }
//    }

}
