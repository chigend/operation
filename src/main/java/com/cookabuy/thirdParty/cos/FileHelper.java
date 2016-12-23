package com.cookabuy.thirdParty.cos;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cookabuy.properties.CosProperties;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.request.UploadFileRequest;
import com.qcloud.cos.sign.Credentials;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 文件助手，该类是对cos的sdk进行简单的封装，方便调用
 *
 * @author yejinbiao
 * @create 2016-12-23-15:34
 */
@EnableConfigurationProperties(CosProperties.class)
@Slf4j
@Component
public class FileHelper {
    private COSClient cosClient;
    public FileHelper(CosProperties properties) {
        Credentials cred = new Credentials(properties.getAppID(), properties.getSecretID(), properties.getSecretKey());
        this.cosClient = new COSClient( cred);
    }

    public String uploadFile(String bucketName,String descPath,byte [] data){

        UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName,descPath, data);
        String response = cosClient.uploadFile(uploadFileRequest);
        JSONObject object = JSON.parseObject(response);
        JSONObject object1 = JSON.parseObject(object.get("data").toString());
        return (String) object1.get("source_url");
    }

    public String uploadFile(String bucketName,String cosPath,String localPath){
        UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName,cosPath, localPath);
        String response = cosClient.uploadFile(uploadFileRequest);
        JSONObject object = JSON.parseObject(response);
        JSONObject object1 = JSON.parseObject(object.get("data").toString());
        return (String) object1.get("source_url");
    }
}
