package com.cookabuy.service;

import com.cookabuy.entity.service.dto.CategoryForm;
import com.cookabuy.entity.service.po.DisplayCategory;
import com.cookabuy.repository.service.CategoryLinkRepository;
import com.cookabuy.repository.service.DisplayCategoryRepository;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yejinbiao
 * @create 2017-02-22-下午2:51
 */
@Service
public class CategoryService {
    @Autowired
    private CategoryLinkRepository categoryLinkRepository;
    @Autowired
    private DisplayCategoryRepository displayCategoryRepository;

    public List<CategoryForm> findAllCategories() {
        List<DisplayCategory> parents = displayCategoryRepository.findAll();
        List<CategoryForm> results = new ArrayList<>();
        parents.stream().forEach(category -> {
            CategoryForm categoryForm = new CategoryForm();
            categoryForm.setParent(category);
            findChildCategories(categoryForm);
            results.add(categoryForm);
        });
        return results;
    }

    private void findChildCategories(CategoryForm category) {
        List <DisplayCategory> childs = displayCategoryRepository.findByPid(category.getParent().getId());
        if (CollectionUtils.isNotEmpty(childs)) {
            childs.stream().forEach(child -> findChildCategories(category));
        }
        category.setChilds(childs);
    }
}
