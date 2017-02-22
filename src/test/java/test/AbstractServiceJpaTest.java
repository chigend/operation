package test;

import com.cookabuy.configuration.ServiceJpaConfiguration;
import config.TestServiceDataSourceConfiguration;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author yejinbiao
 * @create 2017-02-17-下午3:54
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestServiceDataSourceConfiguration.class, ServiceJpaConfiguration.class})

@ComponentScan(value = {"com.cookabuy.repository.service","com.cookabuy.service"})
public abstract class AbstractServiceJpaTest {
}
