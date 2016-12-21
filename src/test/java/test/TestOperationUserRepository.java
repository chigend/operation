package test;

import com.cookabuy.entity.operation.po.Menu;
import com.cookabuy.entity.operation.po.OperationUser;
import com.cookabuy.entity.operation.po.Permission;
import com.cookabuy.repository.operation.OperationUserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 2016/12/12
 */
public class TestOperationUserRepository extends AbstractJpaTest {
    @Autowired
    private OperationUserRepository userRepository;
    @Test
    public void test(){
        OperationUser user = userRepository.findByUsername("zhangsan");
        System.out.println("-------------------------");
        List<Menu> menuList = user.getMenus();
        menuList.stream().map(Menu::getName).forEach(System.out::println);
        System.out.println("-------------------------");

        List<Permission> permissions = user.getPermissions();

        permissions.stream().map(Permission::getPermission).forEach(System.out::println);
    }

    @Test
    public void testAddUser(){
        OperationUser user = new OperationUser();
        user.setPassword("hello");
        user.setUsername("wangwu");
        user = userRepository.save(user);
        System.out.println(user.getId());
    }
    @Test
    public void testUserList(){
        userRepository.findAllNotAdminOperationUser().stream().map(OperationUser::getUsername).forEach(System.out::println);
    }

}
