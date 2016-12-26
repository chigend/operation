package test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.exception.AbstractCosException;
import com.qcloud.cos.request.DelFileRequest;
import com.qcloud.cos.request.UploadFileRequest;
import com.qcloud.cos.sign.Credentials;
import com.qcloud.cos.sign.Sign;
import entity.Student;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

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

}
