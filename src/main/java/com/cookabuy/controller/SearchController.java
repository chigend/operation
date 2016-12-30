package com.cookabuy.controller;

import com.cookabuy.service.SearchService;
import com.cookabuy.thirdParty.elasticsearch.ItemQuery;
import com.cookabuy.thirdParty.elasticsearch.StoreQuery;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yejinbiao
 * @create 2016-12-27-13:49
 */
@RestController
@RequestMapping("operate")
@Slf4j
public class SearchController {
    @Autowired
    private SearchService searchService;

    @RequestMapping("/search_item")
    public SearchResponse searchItem(ItemQuery itemQuery) {
        return searchService.searchItems(itemQuery);
    }

    @RequestMapping("/search_store")
    public SearchResponse searchItem(StoreQuery storeQuery) {
        return searchService.searchStores(storeQuery);
    }
}
