package test;

import com.cookabuy.repository.service.CategoryLinkRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * @author yejinbiao
 * @create 2017-02-23-上午10:04
 */

public class TestCategoryLinkRepository extends AbstractServiceJpaTest {
    @Autowired
    private CategoryLinkRepository categoryLinkRepository;

}
