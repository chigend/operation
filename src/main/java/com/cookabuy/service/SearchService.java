package com.cookabuy.service;

import com.cookabuy.constant.ElasticSearchConstant;
import com.cookabuy.thirdParty.elasticsearch.ItemQuery;
import com.cookabuy.thirdParty.elasticsearch.StoreQuery;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 做查询的service
 *
 * @author yejinbiao
 * @create 2016-12-19-上午9:51
 */
@Service
public class SearchService {
    @Autowired
    private TransportClient client;


    public SearchResponse searchItems(ItemQuery query){
        SearchRequestBuilder requestBuilder = client.prepareSearch("operation").setTypes("item");

        if(StringUtils.hasLength(query.getTitle())){
            requestBuilder.setQuery(QueryBuilders.termQuery("title",query.getTitle()));
        }
        if(StringUtils.hasLength(query.getStore())){
            requestBuilder.setQuery(QueryBuilders.termQuery("store_name",query.getStore()));
        }
        requestBuilder.setPostFilter(
                QueryBuilders.rangeQuery("price")
                            .from(query.getPriceLow())
                            .to(query.getPriceHight())
        );
        //设置默认的排序方式，价格优先升序排序，上架时间降序排序
        requestBuilder.addSort("price", SortOrder.ASC).addSort("list_time",SortOrder.DESC).setFrom(query.getFrom()).setSize(query.getSize());
        return requestBuilder.get();
    }

    public SearchResponse searchStores(StoreQuery query){
        SearchRequestBuilder requestBuilder = client.prepareSearch(ElasticSearchConstant.INDEX_NAME_OPERATION).setTypes(ElasticSearchConstant.TYPE_NAME_STORE);

        if(StringUtils.hasLength(query.getStoreName())){
            requestBuilder.setQuery(QueryBuilders.termQuery("store_name",query.getStoreName()));
        }
        if(StringUtils.hasLength(query.getLocation())){
            requestBuilder.setQuery(QueryBuilders.termQuery("location",query.getLocation()));
        }

        //设置默认的排序方式，价格优先升序排序，上架时间降序排序
        requestBuilder.setFrom(query.getFrom()).setSize(query.getSize());
        return requestBuilder.get();
    }

}
