package com.cookabuy.controller;

import com.cookabuy.entity.service.dto.*;
import com.cookabuy.entity.service.po.*;
import com.cookabuy.repository.service.*;
import com.cookabuy.service.CategoryService;
import com.cookabuy.thirdParty.dozer.DozerHelper;
import com.cookabuy.util.Result;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
    private DozerHelper dozerHelper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryLinkRepository categoryLinkRepository;

    @Autowired
    private ActiveDisplayCategoryRepository activeDisplayCategoryRepository;

    @Autowired
    private ActiveCategoryLinkRepository activeCategoryLinkRepository;

    @RequestMapping("list_display")
    public Result listCategories(Result result) {
        result.addData("cats", categoryService.findAllCategories());
        return result;
    }

    @RequestMapping("add_category")
    public Result addCategory(@RequestBody AddDisplayCategoryForm form) {
        Integer cid = form.getCid();
        if (cid == null) {
            DisplayCategory category = mapper.map(form, DisplayCategory.class);
            UUID id = UUID.randomUUID();
            category.setId(id);
            if (category.getPid() == null) {
                category.setPid(id);
            }
            if (category.getWeight() == null) {
                category.setWeight(0);
            }
            displayCategoryRepository.save(category);
        }else {
            CategoryLink link = new CategoryLink(form.getPid(), cid, form.getName());
            categoryLinkRepository.save(link);
        }
        return new Result();
    }
    @RequestMapping("update_category")
    public Result updateCategory(UpdateDisplayCategoryForm form) {
        //todo  表单合法性验证
        displayCategoryRepository.updateDisplatCategory(form.getName(),form.isDisplay(),form.getWeight(),form.getId());
        return new Result();
    }
    @RequestMapping("list_links")
    public Result listLinks(@RequestParam UUID pid) {
        List<CategoryLink> links = categoryLinkRepository.findByDisplayId(pid);
        List<FrontCategoryLink> flinks = dozerHelper.mapList(links, FrontCategoryLink.class);
        return new Result("cats", flinks);
    }

    @RequestMapping("delete_link")
    public Result deleteLink(Integer cid) {
        categoryLinkRepository.deleteByCid(cid);
        return new Result();
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
        List<FrontCategory> frontCategories = dozerHelper.mapList(categories, FrontCategory.class);
        return new Result("categories", frontCategories);
    }

    @RequestMapping("publish_category")
    public Result publishCategories(@RequestBody List<PublishCategoryDTO> dtos) {
        //先保存当前的状态
        dtos.stream().forEach(dto -> {
            DisplayCategory category = displayCategoryRepository.findOne(dto.getId());
            if (category != null) {
                category.setWeight(dto.getWeight());
                category.setDisplay(dto.isDisplay());
                displayCategoryRepository.save(category);
            }
        });

        //先删除已发布的内容
        displayCategoryRepository.deleteAll();
        //再发布新的内容
        displayCategoryRepository.findAll().stream().filter(DisplayCategory::isDisplay).forEach(category -> {
            ActiveDisplayCategory activeCategory = mapper.map(category, ActiveDisplayCategory.class);
            activeDisplayCategoryRepository.save(activeCategory);
        });

        categoryLinkRepository.deleteAll();
        categoryLinkRepository.findAll().stream().forEach(link -> {
            ActiveCategoryLink activeLink = mapper.map(link, ActiveCategoryLink.class);
            activeCategoryLinkRepository.save(activeLink);
        });
        return new Result();
    }
}
