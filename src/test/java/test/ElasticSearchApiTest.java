package test;

import com.cookabuy.thirdParty.elasticsearch.ItemQuery;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.net.InetAddress;

import static com.cookabuy.constant.ElasticSearchConstant.*;
import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;
import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * @author yejinbiao
 * @create 2016-12-28-17:11
 */

public class ElasticSearchApiTest {
    TransportClient client;
    Client client2 ;
    @Before
    public void init() throws Exception{

        client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("119.29.205.164"), 9300));

    }

    @Test
    public void testGetStorePicUrl () throws Exception {
        GetResponse response = client.prepareGet(STORE_INDEX_NAME, TYPE_NAME_STORE, "3686").get();
        String url =(String) response.getSourceAsMap().get("pic_url");
        System.out.println(url);
    }

    @Test
    public void testUpdateStore () {
        UpdateRequest updateRequest = new UpdateRequest(STORE_INDEX_NAME,
                TYPE_NAME_STORE, "7ad50c09-ab68-43fc-8d1b-6969c5abdd20");
        String url = "http://test-1252811756.cosgz.myqcloud.com/store/8f9f8f15-83e2-4d3e-85e4-9b324c1bfda3.jpg";
        try {

            updateRequest.doc(jsonBuilder()
                    .startObject()
                    .field("pic_url", url)
                    .endObject());
            client.update(updateRequest).get();
        }catch (Exception e){
            System.out.print(e);
        }
    }
    @Test
    public void testGetItemUrl() {
            GetResponse response = client.prepareGet(ITEM_INDEX_NAME, TYPE_NAME_ITEM, String.valueOf(535482154236L)).get();
            String s =  (String) response.getSourceAsMap().get("pic_url");
            System.out.println(s);
        }

    @Test
    public void testUpdateItemUrl() {
        UpdateRequest updateRequest = new UpdateRequest(ITEM_INDEX_NAME,
                TYPE_NAME_ITEM, "6be1a96a-c313-43fd-b8a7-5e558fe2c094");
        String url = "http://test-1252811756.cosgz.myqcloud.com/item/74941b2e-bb58-4cc3-ab6e-1d1462bf28d5.jpg";
        try {

            updateRequest.doc(jsonBuilder()
                    .startObject()
                    .field("pic_url", url)
                    .endObject());
            client.update(updateRequest).get();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Test
    public void testSearch() {
        ItemQuery query = new ItemQuery();
        SearchRequestBuilder requestBuilder = client.prepareSearch(ITEM_INDEX_NAME).setTypes(TYPE_NAME_ITEM);
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
        requestBuilder.setFrom(query.getFrom()).setSize(query.getSize());
//        requestBuilder.addAggregation(AggregationBuilders.terms("markets").field("market"));
        requestBuilder.get().toString();
    }
}
