package com.cookabuy.service;

import com.cookabuy.constant.CosConstant;
import com.cookabuy.constant.ElasticSearchConstant;
import com.cookabuy.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * elasticsearch 的更新索引的service
 *
 * @author yejinbiao
 * @create 2016-12-28-9:57
 */
@Service
@Slf4j
public class UpdateService {

    @Autowired
    private TransportClient client;

    public Result updateStoreUrl(Long storeId, String url){
        UpdateRequest updateRequest = new UpdateRequest(ElasticSearchConstant.INDEX_NAME_OPERATION,
                ElasticSearchConstant.TYPE_NAME_STORE, String.valueOf(storeId));
        try {

            updateRequest.doc(jsonBuilder()
                    .startObject()
                    .field("pic_url", url)
                    .endObject());
            client.update(updateRequest).get();
        }catch (Exception e){
            log.warn("更新索引失败 reason:{}",e.getCause());
            return new Result("图片更新失败");
        }
        Result result = new Result();
        result.addData("picUrl",url);
        return result;
    }
}
