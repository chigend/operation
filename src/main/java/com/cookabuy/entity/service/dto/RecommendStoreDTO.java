package com.cookabuy.entity.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * @author yejinbiao
 * @create 2016-12-28-10:34
 */
@Setter
@Getter
public class RecommendStoreDTO {
    private UUID id;

    private UUID storeId;

    private String storeName;

    private String location;

    private String picUrl;

    private Integer position;

    private boolean effective;
}
