package test;

import com.cookabuy.repository.operation.PermissionRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yejinbiao
 * @create 2016-12-30-14:01
 */

public class TestPermissionRepository extends AbstractJpaTest {
    @Autowired
    private PermissionRepository permissionRepository;
    @Test
    public void testGetPermissionNameList() {
        permissionRepository.findPermissionNameByUserId(4).stream().forEach(System.out  :: println);
    }
}
