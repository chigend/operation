package com.cookabuy.service;

import com.cookabuy.thirdParty.elasticsearch.ItemQuery;
import com.cookabuy.thirdParty.elasticsearch.StoreQuery;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import static com.cookabuy.constant.ElasticSearchConstant.*;
import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * elasticsearch的查询的service
 *
 * @author yejinbiao
 * @create 2016-12-19-上午9:51
 */
@Service
@Slf4j
public class SearchService {
    @Autowired
    private TransportClient client;


    public SearchResponse searchItems(ItemQuery query){
        SearchRequestBuilder requestBuilder = client.prepareSearch(INDEX_NAME_OPERATION).setTypes(TYPE_NAME_ITEM);
        //定义组合查询
        BoolQueryBuilder boolQuery = boolQuery().must(matchQuery("added", false));
        if(StringUtils.hasLength(query.getTitle())){
            boolQuery.must(termQuery("title", query.getTitle()));
        }
        if(StringUtils.hasLength(query.getStore())){
            boolQuery.must(matchQuery("store_name", query.getStore()));
        }
        requestBuilder.setPostFilter(
                rangeQuery("price")
                            .from(query.getPriceLow())
                            .to(query.getPriceHight())
        );
        requestBuilder.setQuery(boolQuery);
//        //设置默认的排序方式，价格优先升序排序，上架时间降序排序
//        requestBuilder.addSort("price", SortOrder.ASC).addSort("list_time",SortOrder.DESC).setFrom(query.getFrom()).setSize(query.getSize());
        return requestBuilder.get();
    }

    public SearchResponse searchStores(StoreQuery query){
        SearchRequestBuilder requestBuilder = client.prepareSearch(INDEX_NAME_OPERATION).setTypes(TYPE_NAME_STORE);

        BoolQueryBuilder booleanQuery = boolQuery().must(matchQuery("added", false));
        if(StringUtils.hasLength(query.getStoreName())){
            booleanQuery.must(matchQuery("store_name", query.getStoreName()));
        }
        if(StringUtils.hasLength(query.getLocation())){
            booleanQuery.must(matchQuery("location", query.getLocation()));
        }
        requestBuilder.setQuery(booleanQuery);
        requestBuilder.setFrom(query.getFrom()).setSize(query.getSize());
        return requestBuilder.get();
    }

}
