package test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cookabuy.util.Result;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.exception.AbstractCosException;
import com.qcloud.cos.request.DelFileRequest;
import com.qcloud.cos.request.UploadFileRequest;
import com.qcloud.cos.sign.Credentials;
import entity.Student;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.util.*;

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
      List<Student> studentList = Arrays.asList(s1,s2,s3,s4,s5,s6);
      studentList.stream().distinct().forEach(System.out::println);
   }

   @Test
   public void testForceCastNull(){
      Student s = null;
      Object o = (Object)s;
      System.out.print(o);
   }

   @Test
   public void uploadImg() throws IOException, AbstractCosException {
      String localPath = "C:\\Users\\Administrator\\Desktop\\1.jpg";
      String bucketName = "test";
      long appId = 1252811756;
      String secretId = "AKIDa3RH1d7PWTDlZIbLeljVDj2MdjdKZ6C6";
      String secretKey = "vfDYhglhT3c2KUSEsKZcUAewYEyXyFMI";
      // 设置要操作的bucket
      // 初始化秘钥信息
      Credentials cred = new Credentials(appId, secretId, secretKey);
      COSClient cosClient = new COSClient( cred);
      UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName,"/ad/3.jpg", localPath);
      String uploadFileRet = cosClient.uploadFile(uploadFileRequest);
      System.out.println(uploadFileRet);
      JSONObject object = JSON.parseObject(uploadFileRet);
      JSONObject object1 = JSON.parseObject(object.get("data").toString());
      System.out.println(object1.get("source_url"));

   }
   @Test
   public void testGetCosPath(){
      String url = "http://test-1252811756.cosgz.myqcloud.com/ad/1.jpg";
      String cosPath = com.cookabuy.util.FileUtils.getCosPath(url);
      System.out.println(cosPath);
   }

   @Test
   public void testCosDeleteFile(){
      String bucketName = "test";
      long appId = 1252811756;
      String secretId = "AKIDa3RH1d7PWTDlZIbLeljVDj2MdjdKZ6C6";
      String secretKey = "vfDYhglhT3c2KUSEsKZcUAewYEyXyFMI";
      // 设置要操作的bucket
      // 初始化秘钥信息
      Credentials cred = new Credentials(appId, secretId, secretKey);
      COSClient cosClient = new COSClient( cred);
      DelFileRequest delFileRequest = new DelFileRequest(bucketName,"/ad/3.jpg");
      String uploadFileRet = cosClient.delFile(delFileRequest);
      System.out.println(uploadFileRet);
      JSONObject object = JSON.parseObject(uploadFileRet);
      System.out.println(object.getInteger("code"));
   }

   @Test
   public void testResult () {
      Result result = new Result();
   }

   @Test
   public void testIterator() {
      List<String > strings = new ArrayList<>();
      strings.add("hello");
      strings.add("word");
      strings.add("heihei");
      for (Iterator<String> it = strings.iterator(); it.hasNext();) {
         String str = it.next();
         if (str.equals("hello")) {
            it.remove();
         }
      }
      System.out.println(strings);
   }
   @Test
   public void testPattern() {
      String pattern = "0||[1-4][0-9]||5[1-5]";
      for (int i = 0; i < 100; i++) {
         System.out.println(i + ":" + (String.valueOf(i)).matches(pattern));
      }
   }

   @Test
   public void testTreeMap() {
      Map<String, Integer> order = new HashMap<>();
      order.put("频道管理", 2);
      order.put("公共板块管理", 3);
      order.put("首页管理", 1);
      order.put("账户管理", 4);
      Map<String, String> map = new TreeMap<>(new Comparator<String>() {
          @Override
          public int compare(String o1, String o2) {
              return order.get(o1) - order.get(o2);
          }
      });
      map.put("账户管理", "account manage");
      map.put("公共板块管理", "public manage");
      map.put("频道管理", "channel manage");
      map.put("首页管理", "index manage");

      Collection<String> strings = map.values();
      for (String s : strings) {
         System.out.println(s);
      }


   }

   @Test
   public void testSubstring () {
      String location = "新百佳 - 大门口A - A11022";
      int index  = location.indexOf('-');
      location = location.substring(index + 1);
      System.out.println(location);
   }

   @Test
   public void testPage() {
      int total = 10;
      int size = 5;
      int index = 9;
      int left = index - size/2;
      int right = index + size/2;
      int start = left < 0 ? 1: (right > 100 ? total-size-1: left);
      System.out.println(start);
   }


   @Test
   public void testCoffee() {
      int money = 100;
      int oneTurnCoffee = money/20;
      int totalCoffee = oneTurnCoffee;
      int cover = oneTurnCoffee;
      int cup = oneTurnCoffee;
      System.out.printf("有杯盖%d个,空杯%d个，总共喝了%d杯咖啡",cover,cup,totalCoffee);
      System.out.println();
      while (!(cover < 4 && cup < 2)) {
         int coverTurn = cover / 4;
         System.out.printf("使用盖子%d个换了%d杯咖啡",coverTurn*4,coverTurn);
         System.out.println();
         cover = cover -  coverTurn*3;
         cup += coverTurn;
         totalCoffee += coverTurn;
         System.out.printf("有杯盖%d个,空杯%d个，总共喝了%d杯咖啡",cover,cup,totalCoffee);
         System.out.println();
         int cupTurn = cup / 2;
         System.out.printf("使用空杯%d个换了%d杯咖啡",cupTurn*2,cupTurn);
         System.out.println();
         cup = cup - cupTurn;
         cover += cupTurn;
         totalCoffee += cupTurn;
         System.out.printf("有杯盖%d个,空杯%d个，总共喝了%d杯咖啡",cover,cup,totalCoffee);
         System.out.println();

      }
      System.out.println(totalCoffee);
   }

}
