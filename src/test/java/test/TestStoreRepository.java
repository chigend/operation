package test;

import com.cookabuy.repository.service.StoreRepository;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.InetAddress;

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

    }
}
