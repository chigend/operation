package test;

import com.alibaba.fastjson.JSON;
import com.cookabuy.entity.operation.dto.DisplayMenu;
import com.cookabuy.entity.operation.po.Menu;
import com.cookabuy.repository.operation.MenuRepository;
import com.cookabuy.thirdParty.dozer.DozerHelper;
import com.cookabuy.util.selector.MenuSelector;
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

    @Test
    public void testone(){
        Menu menu = menuRepository.findOne(1);
        System.out.println(JSON.toJSON(menu));
    }

    @Test
    public void testtwo(){
      List<Menu> menus =  menuRepository.findAll();
        System.out.println(JSON.toJSONString(menus));
    }
    @Test
    public void testFindMenuForOrdinaryUser() {
        menuRepository.findAllMenuForOrdinaryUser().stream().map(Menu :: getName).forEach(System.out :: println);
    }
}
