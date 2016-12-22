package com.cookabuy.util.selector;

import com.cookabuy.entity.operation.po.Menu;

/**
 * @author yejinbiao
 * @create 2016-12-22-14:20
 */

public class Menu2Selector extends  Selector<String,Menu> {
    @Override
    protected String selectCondition(Menu menu) {
        return menu.getCategory();
    }
}
