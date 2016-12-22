package test;

import com.cookabuy.repository.operation.OperationRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yejinbiao
 * @create 2016-12-22-14:37
 */

public class TestOperationRepository extends AbstractJpaTest {
    @Autowired
    private OperationRepository operationRepository;
    @Test
    public void test(){
        operationRepository.findOperationIdsByUserId(1).stream().forEach(System.out::println);
    }
}
