package com.cookabuy.entity.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * @author yejinbiao
 * @create 2017-02-21-上午10:37
 */
@Setter
@Getter
public class UpdateRecommendStoreForm {
    private UUID id;
    private String picUrl;
    private UUID storeId;
}
