package test;

import com.cookabuy.entity.operation.po.UserMenu;
import com.cookabuy.repository.operation.UserMenuRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yejinbiao
 * @create 2016-12-13-下午3:51
 */

public class TestUserMenuRepository extends AbstractJpaTest{
    @Autowired
    private UserMenuRepository userMenuRepository;
    @Test
    public void test(){
        userMenuRepository.findAll().stream().map(UserMenu::getMenuId).forEach(System.out::println);
    }

}
