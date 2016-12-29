package com.cookabuy.service;

import com.cookabuy.constant.CosConstant;
import com.cookabuy.constant.ElasticSearchConstant;
import com.cookabuy.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static com.cookabuy.constant.ElasticSearchConstant.INDEX_NAME_OPERATION;
import static com.cookabuy.constant.ElasticSearchConstant.TYPE_NAME_ITEM;
import static com.cookabuy.constant.ErrorConstant.UPDATE_IMAGE_FAIL;
import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import static com.cookabuy.constant.ElasticSearchConstant.*;
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

    public Result updateStoreUrl(Long storeId, String url) {
        UpdateRequest updateRequest = new UpdateRequest(INDEX_NAME_OPERATION,
                TYPE_NAME_STORE, String.valueOf(storeId));
        try {

            updateRequest.doc(jsonBuilder()
                    .startObject()
                    .field("pic_url", url)
                    .endObject());
            client.update(updateRequest).get();
        } catch (Exception e) {
            log.warn("更新索引失败 reason:{}", e.getCause());
            return new Result(UPDATE_IMAGE_FAIL);
        }
        Result result = new Result();
        result.addData("picUrl", url);
        return result;
    }

    public Result updateItemUrl(Long itemId, String url) {
        Map<String, Object> source = new HashMap<>();
        source.put("pic_url", url);
        Result result = updateField(INDEX_NAME_OPERATION, TYPE_NAME_ITEM, itemId, source);
        result.ifSuccess(() -> result.addData("pic_url", url));
        return result;
    }

    private Result updateField(String index, String type, Long id, Map<String, Object> source) {
        UpdateRequest updateRequest = new UpdateRequest(index,
                type, String.valueOf(id));
        try {

            updateRequest.doc(source);
            client.update(updateRequest).get();
        } catch (Exception e) {
            log.warn("更新索引失败 reason:{}", e.getCause());
            return new Result("更新失败");
        }
        return new Result();
    }
}
