package com.cookabuy.entity.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author yejinbiao
 * @create 2016-12-29-13:47
 */
@Setter
@Getter
public class RecommendItemEntity {
    private String pageName;

    private String location;

    private List<RecommendItemDTO> items;
}
