package test;

import com.cookabuy.repository.service.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2016/12/7
 */
@Slf4j
public class TestItemRepository extends AbstractJpaTest {
    @Autowired
    private ItemRepository itemRepository;

    TransportClient client;
    @Before
    public void init() throws Exception{
        client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
    }

    @Test
    public void testJoinStore() throws Exception {

//
//        List<IndexResponse> responses = new ArrayList<IndexResponse>();
//        itemRepository.findTop1000().stream().forEach(item -> {
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.put("list_time", item.getListTime());
//            map.put("market", item.getStore().getMarket());
//            map.put("price", item.getPrice());
//            map.put("store_name", item.getShopName());
//            map.put("title", item.getTitle());
//            IndexResponse response = client.prepareIndex("operation","item",String.valueOf(item.getNumIid())).setSource(map).get();
//            responses.add(response);
//        });
//        String json = JSONArray.toJSONString(responses);
//        System.out.println(json);
//        client.close();
    }



    @Test(timeout = 60000*30)
    public void testFindTop() {
        int numOfOneTurn = 1000;
        long count = itemRepository.count();
        int offset = 0;
        List<IndexResponse> responses = new ArrayList<IndexResponse>();
        for (long i=1;i<=Math.ceil((double)count/numOfOneTurn);i++){
            BulkRequestBuilder bulkRequest = client.prepareBulk();
            itemRepository.findTopAndOffset(numOfOneTurn,offset).stream().forEach(item -> {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("list_time", item.getListTime());
                map.put("market", item.getStore().getMarket());
                map.put("price", item.getPrice());
                map.put("store_name", item.getStore().getStoreName());
                map.put("title", item.getTitle());
                IndexResponse response = client.prepareIndex("operation","item",String.valueOf(item.getNumIid())).setSource(map).get();
                responses.add(response);
            });
            offset = (int)(numOfOneTurn*i);
        }

    }
}
