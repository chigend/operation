package com.cookabuy.util.selector;

import com.cookabuy.entity.operation.dto.DisplayMenu;

import java.util.Collection;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * 菜单分拣器，根据菜单的category字段进行分拣
 *
 * @author yejinbiao
 * @create 2016-12-21-16:28
 */

public class MenuSelector extends Selector<String,DisplayMenu>{

    public MenuSelector(Collection<DisplayMenu> menus) {
        this.select(menus);
    }

    public MenuSelector(Collection<DisplayMenu> menus, Comparator<String> comparator) {
        cabinet = new TreeMap<>(comparator);
        this.select(menus);
    }
    public MenuSelector() {
    }

    @Override
    protected String selectCondition(DisplayMenu menu) {
        return menu.getCategory();
    }
}
