package com.cookabuy.entity.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

/**
 * 修改广告时所提交的表单
 *
 * @author yejinbiao
 * @create 2016-12-26-13:51
 */
@Setter
@Getter
@ToString
public class UpdateAdForm {
    private Integer adId;

    private MultipartFile image;

    private String activityUrl;
}
