package com.cookabuy.entity.service.dto;

import com.cookabuy.entity.service.po.DisplayCategory;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author yejinbiao
 * @create 2017-02-22-下午2:53
 */
@Setter
@Getter
public class CategoryForm {
    private DisplayCategory parent;
    private List<DisplayCategory> childs;
}
