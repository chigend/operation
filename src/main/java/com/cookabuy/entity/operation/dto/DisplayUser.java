package com.cookabuy.entity.operation.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author yejinbiao
 * @create 2016-12-21-14:13
 */
@Setter
@Getter
public class DisplayUser {

    private String realName;

    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date createTime;

    private String username;
}
