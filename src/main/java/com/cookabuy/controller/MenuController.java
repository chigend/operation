package com.cookabuy.controller;

import com.cookabuy.entity.operation.dto.DisplayMenu;
import com.cookabuy.entity.operation.dto.MenuForAddUser;
import com.cookabuy.entity.operation.po.Menu;
import com.cookabuy.repository.operation.MenuRepository;
import com.cookabuy.util.DozerHelper;
import com.cookabuy.util.Result;
import com.cookabuy.util.ShiroHelper;
import com.cookabuy.util.selector.MenuSelector;
import com.cookabuy.util.selector.Selector;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 2016/12/13
 */
@Controller
@RequestMapping("/operate")
public class MenuController {
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private DozerHelper dozerHelper;
    @RequestMapping("/menu_list2")
    @ResponseBody
    public  List<MenuForAddUser> menuList(){
        List<Menu> menus = menuRepository.findAll();
        List<MenuForAddUser> menuList = dozerHelper.mapList(menus,MenuForAddUser.class);
        return menuList;
    }


    @RequestMapping("/menus")
    public Result getMenus(Result result){
        Integer userId = ShiroHelper.getCurrentUserId();
        List<Menu> menus = menuRepository.findMenuByUserId(userId);
        //过滤掉po中不需要展示给前端的属性
        List<DisplayMenu> displayMenus = dozerHelper.mapList(menus,DisplayMenu.class);
        Selector menuSelector = new MenuSelector(displayMenus);

        result.addData("menus",menuSelector.getSelecResult());

        return result;

    }

}
