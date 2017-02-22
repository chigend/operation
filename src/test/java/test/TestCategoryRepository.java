package test;

import com.cookabuy.service.CategoryService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yejinbiao
 * @create 2017-02-22-下午4:12
 */
@ComponentScan(value = "com.cookabuy.service")
public class TestCategoryRepository extends AbstractServiceJpaTest {
    @Autowired
    private CategoryService categoryService;
    @Test
    public void test() {
        System.out.println(categoryService.findAllCategories());
    }
}
