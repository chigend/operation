package com.cookabuy.service;

import com.cookabuy.entity.service.po.RecommendStore;
import com.cookabuy.repository.service.RecommendItemRepository;
import com.cookabuy.repository.service.RecommendStoreRepository;
import com.cookabuy.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    @Autowired
    private RecommendStoreRepository recommendStoreRepository;

    @Autowired
    private RecommendItemRepository recommendRepository;

    public Result updateStoreUrl(UUID storeId, String url) {
//        UpdateRequest updateRequest = new UpdateRequest(INDEX_NAME_OPERATION,
//                TYPE_NAME_STORE, String.valueOf(storeId));
//        try {
//
//            updateRequest.doc(jsonBuilder()
//                    .startObject()
//                    .field("pic_url", url)
//                    .endObject());
//            client.update(updateRequest).get();
//        } catch (Exception e) {
//            log.warn("更新索引失败 reason:{}", e.getCause());
//            return new Result(UPDATE_IMAGE_FAIL);
//        }
//        Result result = new Result();
//        result.addData("picUrl", url);
//        return result;
        Map<String, Object> source = new HashMap<>();
        source.put("pic_url", url);
        Result result = updateField(STORE_INDEX_NAME, TYPE_NAME_STORE,  storeId.toString(), source);
        result.ifSuccess(() -> result.addData("pic_url", url));
        return result;
    }

    public Result updateItemUrl(UUID itemId, String url) {
        Map<String, Object> source = new HashMap<>();
        source.put("pic_url", url);
        Result result = updateField(ITEM_INDEX_NAME, TYPE_NAME_ITEM, itemId.toString(), source);
        result.ifSuccess(() -> result.addData("pic_url", url));
        return result;
    }

    /**
     * 根据storeId 切换elasticsearch上store的是否已添加field  added
     * 在添加推荐店铺和删除店铺时分别调用
     * @see com.cookabuy.controller.RecommendStoreController#recommendStore(List)
     * @see com.cookabuy.controller.RecommendStoreController#deleteStore(UUID, Result)
     * @param storeId
     * @return
     */
    public Result toggleStoreAdded(UUID storeId) {
        RecommendStore store = recommendStoreRepository.findByStoreId(storeId);
        boolean added = store == null; //如果推荐店铺为空，则表示即将添加该店铺
        Map<String, Object> source = new HashMap<>();
        source.put("added", added);
        Result result = updateField(STORE_INDEX_NAME, TYPE_NAME_STORE, storeId.toString(), source);
        return result;
    }

    private Result updateField(String index, String type, String id, Map<String, Object> source) {
        UpdateRequest updateRequest = new UpdateRequest(index,
                type, id);
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
