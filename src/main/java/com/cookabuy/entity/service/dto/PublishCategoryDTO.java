package com.cookabuy.entity.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * @author yejinbiao
 * @create 2017-02-24-上午9:37
 */
@Setter
@Getter
public class PublishCategoryDTO {
    private UUID id;
    private Integer weight;
    private boolean display;
}
