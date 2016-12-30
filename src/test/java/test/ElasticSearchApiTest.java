package test;

import com.cookabuy.constant.ElasticSearchConstant;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;

import static com.cookabuy.constant.ElasticSearchConstant.*;
import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * @author yejinbiao
 * @create 2016-12-28-17:11
 */

public class ElasticSearchApiTest {
    TransportClient client;
    @Before
    public void init() throws Exception{
        client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.11.10"), 9300));
    }

    @Test
    public void testGetStorePicUrl () throws Exception {
        GetResponse response = client.prepareGet(INDEX_NAME_OPERATION, TYPE_NAME_STORE, "3686").get();
        String url =(String) response.getSourceAsMap().get("pic_url");
        System.out.println(url);
    }

    @Test
    public void testUpdateStore () {
        UpdateRequest updateRequest = new UpdateRequest(ElasticSearchConstant.INDEX_NAME_OPERATION,
                ElasticSearchConstant.TYPE_NAME_STORE, String.valueOf(3686));
        String url = "http://test-1252811756.cosgz.myqcloud.com/store/8f9f8f15-83e2-4d3e-85e4-9b324c1bfda3.jpg";
        try {

            updateRequest.doc(jsonBuilder()
                    .startObject()
                    .field("pic_url", url)
                    .endObject());
            client.update(updateRequest).get();
        }catch (Exception e){
            System.out.println("更新失败");
        }
    }
    @Test
    public void testGetItemUrl() {
            GetResponse response = client.prepareGet(INDEX_NAME_OPERATION, TYPE_NAME_ITEM, String.valueOf(535482154236L)).get();
            String s =  (String) response.getSourceAsMap().get("pic_url");
            System.out.println(s);
        }

    @Test
    public void testUpdateItemUrl() {
        UpdateRequest updateRequest = new UpdateRequest(INDEX_NAME_OPERATION,
                TYPE_NAME_ITEM, String.valueOf(535482154236L));
        String url = null;
        try {

            updateRequest.doc(jsonBuilder()
                    .startObject()
                    .field("pic_url", url)
                    .endObject());
            client.update(updateRequest).get();
        }catch (Exception e){
            System.out.println("更新失败");
        }
    }

}
