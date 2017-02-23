package com.cookabuy.entity.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * @author yejinbiao
 * @create 2017-02-23-上午9:19
 */
@Setter
@Getter
public class AddDisplayCategoryForm {
    private UUID pid;
    private String name;
    private boolean display;
    private Integer weight;
    private Integer cid;
}
