package com.cookabuy.controller;

import com.cookabuy.constant.SearchType;
import com.cookabuy.elasticsearch.ItemQuery;
import com.cookabuy.entity.service.po.Item;
import com.cookabuy.repository.service.ItemRepository;
import com.cookabuy.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    private SearchService searchService;
    @RequestMapping("/search")
    public SearchResponse searchItem(ItemQuery itemQuery, @RequestParam(defaultValue = "item") String type){
        switch (type){
            case SearchType.ITEM:
                return searchService.searchItems(itemQuery);
            default:
                return searchService.searchItems(itemQuery);
        }
    }

}
