package test;

import com.cookabuy.configuration.OperationJpaConfiguration;
import config.TestOperationDataSourceConfiguration;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author yejinbiao
 * @create 2017-02-17-下午3:57
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestOperationDataSourceConfiguration.class, OperationJpaConfiguration.class})

@ComponentScan("com.cookabuy.repository.operation")
public abstract class AbstractOperationJpaTest {

}
