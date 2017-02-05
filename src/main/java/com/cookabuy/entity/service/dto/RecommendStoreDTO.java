package com.cookabuy.entity.service.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yejinbiao
 * @create 2016-12-28-10:34
 */
@Setter
@Getter
public class RecommendStoreDTO {
    private Integer id;

    private Long storeId;

    private String storeName;

    private String location;

    private String picUrl;

    private boolean effective;
}
