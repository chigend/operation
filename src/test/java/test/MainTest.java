package test;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.transport.TcpTransport;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

/**
 * 2016/12/8
 */


public class MainTest {
   public static  void main(String[]args) throws Exception{
      TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
              .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
      SearchResponse response = client.prepareSearch("operation").setTypes("item").setQuery(QueryBuilders.termQuery("title","修身"))
              .setQuery(QueryBuilders.rangeQuery("price").from(60).to(100)).get();
      System.out.println(response);
      client.close();
   }

}
