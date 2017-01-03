package com.cookabuy.entity.service.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author yejinbiao
 * @create 2016-12-23-13:30
 */
@Setter
@Getter
public class AddAdForm {

    private MultipartFile image;

    private String activityUrl;

    private Integer position;

    private String location;

}
