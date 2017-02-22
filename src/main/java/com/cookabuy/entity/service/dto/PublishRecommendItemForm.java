package com.cookabuy.entity.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author yejinbiao
 * @create 2017-02-21-下午5:32
 */
@Setter
@Getter
public class PublishRecommendItemForm {
    private String page;
    private String location;

    private List<ItemWeight> weights;


}
