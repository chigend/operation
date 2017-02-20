package com.cookabuy.entity.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

/**
 * @author yejinbiao
 * @create 2016-12-23-11:44
 */
@Setter
@Getter
public class DisPlayAd {
    private UUID adId;

    private String picUrl;

    private String tip;

    private String activityUrl;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date createTime;

    private String pageName;

    private boolean isHidden;
}
