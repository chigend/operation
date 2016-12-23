package com.cookabuy.service;

import org.springframework.stereotype.Service;

/**
 * 做查询的service
 *
 * @author yejinbiao
 * @create 2016-12-19-上午9:51
 */
@Service
public class SearchService {
//    @Autowired
//    private TransportClient client;
//
//
//    public SearchResponse searchItems(ItemQuery query){
//        SearchRequestBuilder requestBuilder = client.prepareSearch("operation").setTypes("item");
//
//        if(StringUtils.hasLength(query.getTitle())){
//            requestBuilder.setQuery(QueryBuilders.termQuery("title",query.getTitle()));
//        }
//        if(StringUtils.hasLength(query.getStore())){
//            requestBuilder.setQuery(QueryBuilders.termQuery("store_name",query.getStore()));
//        }
//        requestBuilder.setPostFilter(
//                QueryBuilders.rangeQuery("price")
//                            .from(query.getPriceLow())
//                            .to(query.getPriceHight())
//        );
//        //设置默认的排序方式，价格优先升序排序，上架时间降序排序
//        requestBuilder.addSort("price", SortOrder.ASC).addSort("list_time",SortOrder.DESC);
//        return requestBuilder.get();
//    }
}
