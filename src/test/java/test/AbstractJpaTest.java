package test;

import com.cookabuy.configuration.OperationJpaConfiguration;
import com.cookabuy.configuration.ServiceJpaConfiguration;
import config.TestDataSourceConfiguration;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by yejinbiao on 2016/10/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDataSourceConfiguration.class, ServiceJpaConfiguration.class,OperationJpaConfiguration.class})

@ComponentScan("com.cookabuy.repository")
public abstract  class AbstractJpaTest {
}
