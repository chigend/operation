package com.cookabuy.controller;

import com.cookabuy.service.CategoryService;
import com.cookabuy.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yejinbiao
 * @create 2017-02-22-下午2:48
 */
@RestController
@RequestMapping("operate")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @RequestMapping("list_categories")
    public Result listCategories(Result result) {
        result.addData("cats", categoryService.findAllCategories());
        return result;
    }
//    @RequestMapping("/add_parent")
//    public Result
}
