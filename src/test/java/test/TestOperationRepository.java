package test;

import com.cookabuy.entity.operation.po.UserOp;
import com.cookabuy.entity.operation.po.UserPermission;
import com.cookabuy.repository.operation.OperationRepository;
import com.cookabuy.repository.operation.UserOpRepository;
import com.cookabuy.repository.operation.UserPermissionRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yejinbiao
 * @create 2016-12-22-14:37
 */

public class TestOperationRepository extends AbstractJpaTest {
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private UserOpRepository userOpRepository;
    @Autowired
    private UserPermissionRepository userPermissionRepository;
    @Test
    public void test(){
        operationRepository.findOperationIdsByUserId(1).stream().forEach(System.out::println);
    }

    @Test
    public void testOperation() {
        operationRepository.findAll().stream().forEach(operation -> {
            userOpRepository.save(new UserOp(1, operation.getId()));
            userPermissionRepository.save(new UserPermission(1, operation.getPermissionId()));
        });
    }
}
