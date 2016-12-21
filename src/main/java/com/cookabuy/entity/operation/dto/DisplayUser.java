package com.cookabuy.entity.operation.dto;


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

    private Date createTime;

    private String username;
}
