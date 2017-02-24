package com.cookabuy.entity.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * @author yejinbiao
 * @create 2017-02-24-下午4:43
 */
@Setter
@Getter
public class UpdateDisplayCategoryForm {
    private UUID id;
    private String name;
    private boolean display;
    private Integer weight;
}
