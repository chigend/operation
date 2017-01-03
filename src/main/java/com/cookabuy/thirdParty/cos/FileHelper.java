package com.cookabuy.thirdParty.cos;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cookabuy.properties.CosProperties;
import com.cookabuy.util.FileUtils;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.request.DelFileRequest;
import com.qcloud.cos.request.UploadFileRequest;
import com.qcloud.cos.sign.Credentials;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

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

    /**
     *
     * @param bucketName cos的bucket空间名字
     * @param directoryPath cos上以bucket为根目录的目录路径
     * @param originFileName 要上传的原始文件名，从中获取文件后缀，上传后的文件名由uuid随机字符串与后缀组成
     * @param bytes 文件内容的byte数组格式
     * @return 上传成功后的文件名 e.g 	http://test-1252811756.cosgz.myqcloud.com/ad/e8c55733-935e-40ab-8dc0-605091d210ed.jpg
     */
    public String uploadFile(String bucketName,String directoryPath,String originFileName,byte [] bytes) {

        String suffix = FileUtils.getSuffix(originFileName);
        String fileName = UUID.randomUUID().toString().concat(suffix);
        UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName, directoryPath.concat(fileName), bytes);
        String res = cosClient.uploadFile(uploadFileRequest);
        JSONObject data = JSON.parseObject(res).getJSONObject("data");
        return data == null ? null : data.getString("source_url");
    }

    public String uploadFile(String bucketName, String directoryPath,
                             MultipartFile file) {
        Objects.requireNonNull(file);

        try {
            return uploadFile(bucketName,directoryPath,file.getOriginalFilename(),file.getBytes());
        }catch (IOException e){
            return null;
        }
    }

    /**
     * 此方法根据文件的url来删除保存在cos上的图片
     * @param bucketName cos上bucket空间的名字
     * @param fileUrl 文件通过uploadFile方法上传文件后所保存的地址，e.g	http://test-1252811756.cosgz.myqcloud.com/ad/e8c55733-935e-40ab-8dc0-605091d210ed.jpg
     * @return 返回api调用的返回结果中的message信息
     */
    public String deleteFile(String bucketName,String fileUrl){
        String cosPath = FileUtils.getCosPath(fileUrl);
        DelFileRequest request = new DelFileRequest(bucketName,cosPath);
        String res = cosClient.delFile(request);
        Integer code = JSONObject.parseObject(res).getInteger("code");
            log.info("delete file {} in bucket:{} {}", fileUrl, bucketName,code==0 ? "successfully" : "fail");
        return JSONObject.parseObject(res).getString("message");
    }

}
