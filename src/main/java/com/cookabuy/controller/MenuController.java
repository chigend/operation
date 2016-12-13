package com.cookabuy.controller;

import com.cookabuy.entity.operation.dto.MenuForAddUser;
import com.cookabuy.entity.operation.po.Menu;
import com.cookabuy.repository.operation.MenuRepository;
import com.cookabuy.util.DozerHelper;
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
@RequestMapping("/api")
public class MenuController {
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private DozerHelper dozerHelper;
    @RequestMapping("/menu_list")
    public @ResponseBody List<MenuForAddUser> menuList(){
        List<Menu> menus = menuRepository.findAll();
        List<MenuForAddUser> menuList = dozerHelper.mapList(menus,MenuForAddUser.class);
        return menuList;
    }
}
