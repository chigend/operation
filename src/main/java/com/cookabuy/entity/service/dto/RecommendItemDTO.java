package com.cookabuy.entity.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author yejinbiao
 * @create 2016-12-29-13:26
 */
@Setter
@Getter
@ToString
public class RecommendItemDTO {
    private Integer id;

    private Long itemId;

    private String picUrl;

}
