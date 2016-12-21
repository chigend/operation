package test;

import entity.Student;
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
import org.junit.Test;

import java.net.InetAddress;
import java.util.stream.Stream;

/**
 * 2016/12/8
 */


public class MainTest {
   @Test
   public void testTransportClient() throws Exception{
      TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
              .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
      SearchResponse response = client.prepareSearch("operation").setTypes("item").setQuery(QueryBuilders.termQuery("title","修身"))
              .setQuery(QueryBuilders.rangeQuery("price").from(60).to(100)).get();
      System.out.println(response);
      client.close();
   }
   @Test
   public void test(){
      System.out.println("helloworld");
   }

   @Test
   public void testStreamDistinct(){
      Student s1 = new Student("zhangsan",15);
      Student s2 = new Student("zhangsan",16);
      Student s3 = new Student("zhangsan",17);
      Student s4 = new Student("zhangsan",18);
      Student s5 = new Student("zhangsan",19);
      Student s6 = new Student("zhangsan",20);
      Stream<Student> list = Stream.of(s1,s2,s3,s4,s5,s6);
      list.distinct().forEach(System.out::println);
   }

}
