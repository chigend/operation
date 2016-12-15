package test.search;

import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Elasticsearch repository测试的配置基类，所有es模块的测试都可以继承此类
 *
 * @author yejinbiao
 * @create 2016-12-15-上午11:04
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-elasticsearch.xml"})

@ComponentScan("com.cookabuy.search.repository")

public abstract  class AbstractElasticsearchTest {
}
