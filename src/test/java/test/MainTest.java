package test;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.net.InetAddress;

/**
 * 2016/12/8
 */


public class MainTest {
   public static  void main(String[]args) throws Exception{
      TransportClient client = TransportClient.builder().build()
              .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));

// on shutdown

      client.close();
   }

}
