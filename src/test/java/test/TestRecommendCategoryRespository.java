package test;

import com.cookabuy.entity.service.po.RecommendCategory;
import com.cookabuy.repository.service.RecommendCategoryRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yejinbiao
 * @create 2017-01-04-10:08
 */

public class TestRecommendCategoryRespository extends  AbstractJpaTest {
    @Autowired
    private RecommendCategoryRepository repository;
    @Test
    public void testFindAll() {
        repository.findAll().stream().map(RecommendCategory :: getName).forEach(System.out :: println);
    }
}
