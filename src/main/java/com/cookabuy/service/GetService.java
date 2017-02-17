package com.cookabuy.service;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.cookabuy.constant.ElasticSearchConstant.*;

/**
 * elasticsearch 获取doc的service
 *
 * @author yejinbiao
 * @create 2016-12-28-9:57
 */
@Service
public class GetService {
    @Autowired
    private TransportClient client;

    public String getStorePicUrl (UUID storeId) {
        GetResponse response = client.prepareGet(INDEX_NAME_OPERATION, TYPE_NAME_STORE, storeId.toString()).get();
        return (String) response.getSourceAsMap().get("pic_url");
    }

    public String getItemPicUrl (Long itemId) {
        GetResponse response = client.prepareGet(INDEX_NAME_OPERATION, TYPE_NAME_ITEM, String.valueOf(itemId)).get();
        return (String) response.getSourceAsMap().get("pic_url");
    }


}
