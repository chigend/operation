package com.cookabuy.entity.service.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 2016/12/8
 */
@Setter
@Getter
public class ReplaceRecommendForm {
    private String pageName;

    private String location;

    private Integer position;

    private Long itemId;
}
