package test;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

/**
 * 2016/12/8
 */


public class MainTest {
   public static  void main(String[]args) throws Exception{
      TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
              .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("host1"), 9300))
              .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("host2"), 9300));

// on shutdown

      client.close();
   }

}
