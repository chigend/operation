package test;

import com.cookabuy.repository.service.StoreRepository;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yejinbiao
 * @create 2016-12-27-13:29
 */

public class TestStoreRepository extends AbstractJpaTest {
    private TransportClient client;
    @Autowired
    private StoreRepository storeRepository;
    @Before
    public void init() throws Exception{
        client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
    }
    @Test
    public void indexStore(){
        int numOfOneTurn = 500;
        long count = storeRepository.count();
        int offset = 0;
        for (long i=1;i<=Math.ceil((double)count/numOfOneTurn);i++){
            BulkRequestBuilder bulkRequest = client.prepareBulk();
            storeRepository.findTopAndOffset(numOfOneTurn,offset).stream().forEach(store -> {
                Map<String, Object> map = new HashMap<>();
                map.put("market", store.getMarket());
                map.put("store_name", store.getStoreName());
                map.put("location", store.getLocation());
                map.put("category",store.getCategory() );
                map.put("pic_url", null);
                map.put("added", false);
                bulkRequest.add(client.prepareIndex("operation","store",String.valueOf(store.getId())).setSource(map));
            });
            BulkResponse response = bulkRequest.get();
            if (response.hasFailures()){
                String error = response.buildFailureMessage();
                System.out.println(i+":"+error);
            }
            offset = (int)(numOfOneTurn*i);
        }
    }
}
