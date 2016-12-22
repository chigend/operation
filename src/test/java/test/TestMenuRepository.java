package test;

import com.alibaba.fastjson.JSON;
import com.cookabuy.entity.operation.dto.DisplayMenu;
import com.cookabuy.repository.operation.MenuRepository;
import com.cookabuy.util.DozerHelper;
import com.cookabuy.util.selector.MenuSelector;
import com.cookabuy.util.selector.Selector;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author yejinbiao
 * @create 2016-12-21-16:34
 */

public class TestMenuRepository extends AbstractJpaTest{
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private DozerHelper dozerHelper;
    @Test
    public void test(){
        List<DisplayMenu> menus = dozerHelper.mapList(menuRepository.findMenuByUserId(1),DisplayMenu.class);
        MenuSelector menuSelector = new MenuSelector(menus);

        String result = JSON.toJSONString(menuSelector.getSelectResult());
        System.out.println(result);
    }
}
