package com.cookabuy.controller;

import com.cookabuy.entity.operation.dto.DisplayMenu;
import com.cookabuy.entity.operation.po.Menu;
import com.cookabuy.entity.operation.po.OperationUser;
import com.cookabuy.repository.operation.MenuRepository;
import com.cookabuy.thirdParty.dozer.DozerHelper;
import com.cookabuy.util.Result;
import com.cookabuy.util.ShiroHelper;
import com.cookabuy.util.selector.MenuSelector;
import com.cookabuy.util.selector.Selector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 2016/12/13
 */
@Controller
@RequestMapping("/operate")
@Slf4j
public class MenuController {
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private DozerHelper dozerHelper;

    @RequestMapping("/menus")
    @ResponseBody
    public Result getMenus(Result result, HttpSession session){
        List<Menu> menus = (List<Menu>) session.getAttribute("menus");
        if (menus != null) {
            log.info("get menu from session");
            result.addData("menus", menus);
            return result;
        }
        OperationUser user = ShiroHelper.getCurrentUser();
        Integer userId = user.getId();
        log.info("userId is {}",userId);


        if (user.isAdministrator()) {
            menus = menuRepository.findAll();
        } else {
            menus = menuRepository.findAllMenuForOrdinaryUser();
        }
        log.info("get menus from db");
        //过滤掉po中不需要展示给前端的属性
        List<DisplayMenu> displayMenus = dozerHelper.mapList(menus,DisplayMenu.class);
        Selector menuSelector = new MenuSelector(displayMenus);

        result.addData("menus",menuSelector.getSelectResult());

        session.setAttribute("menus",menus);

        return result;

    }

}
