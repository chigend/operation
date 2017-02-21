package com.cookabuy.entity.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * @author yejinbiao
 * @create 2017-02-21-下午1:22
 */
@Setter
@Getter
public class MoveRecommendStoreForm {
    private UUID id;
    //position after moved
    private Integer position;
}
