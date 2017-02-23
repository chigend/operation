package com.cookabuy.controller;

import com.cookabuy.entity.service.dto.AddDisplayCategoryForm;
import com.cookabuy.entity.service.po.Category;
import com.cookabuy.entity.service.po.CategoryLink;
import com.cookabuy.entity.service.po.DisplayCategory;
import com.cookabuy.repository.service.CategoryLinkRepository;
import com.cookabuy.repository.service.CategoryRepository;
import com.cookabuy.repository.service.DisplayCategoryRepository;
import com.cookabuy.service.CategoryService;
import com.cookabuy.util.Result;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * @author yejinbiao
 * @create 2017-02-22-下午2:48
 */
@RestController
@RequestMapping("operate")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private DisplayCategoryRepository displayCategoryRepository;

    @Autowired
    private DozerBeanMapper mapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryLinkRepository categoryLinkRepository;

    @RequestMapping("list_display")
    public Result listCategories(Result result) {
        result.addData("cats", categoryService.findAllCategories());
        return result;
    }

    @RequestMapping("add_category")
    public Result addCategory(AddDisplayCategoryForm form) {
        Integer cid = form.getCid();
        if (cid == null) {
            DisplayCategory category = mapper.map(form, DisplayCategory.class);
            UUID id = UUID.randomUUID();
            category.setId(id);
            if (category.getPid() == null) {
                category.setPid(id);
            }
            displayCategoryRepository.save(category);
        }else {
            CategoryLink link = new CategoryLink(form.getPid(), cid, form.getName());
            categoryLinkRepository.save(link);
        }
        return new Result();
    }
    @RequestMapping("find_third")
    public Result findThird(@RequestParam UUID pid) {
        List<String> names = categoryLinkRepository.findAliasByDisplayId(pid);
        return new Result("names", names);
    }

    @RequestMapping("delete_category")
    public Result deleteCategory(@RequestParam UUID id) {
        int childsCount = displayCategoryRepository.findChildCount(id);
        if (childsCount > 0 ) {
            return new Result("有子目录无法删除");
        }

        childsCount = categoryLinkRepository.findChildsCount(id);
        if (childsCount > 0) {
            return new Result("有关联的三级目录，无法删除");
        }
        displayCategoryRepository.delete(id);
        return new Result();

    }


    @RequestMapping("list_categories")
    public Result listCategories(Integer pid) {
        List<Category> categories = categoryRepository.findByPid(pid);
        return new Result("categories", categories);
    }


}
