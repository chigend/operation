package com.cookabuy.entity.service.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 保存广告轮播图时所提交的表单，只要根据id修改position即可
 * @author yejinbiao
 * @create 2016-12-26-12:40
 */
@Setter
@Getter
public class SaveAdForm {
    private Integer adId;

    private Integer position;
}
