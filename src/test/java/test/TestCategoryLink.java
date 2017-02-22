package test;

import com.cookabuy.entity.service.po.CategoryLink;
import com.cookabuy.repository.service.CategoryLinkRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * @author yejinbiao
 * @create 2017-02-22-下午2:22
 */

public class TestCategoryLink extends AbstractServiceJpaTest{
    @Autowired
    private CategoryLinkRepository categoryLinkRepository;
    @Test
    public void testaddCategoryLink() {
        categoryLinkRepository.save(new CategoryLink(UUID.randomUUID(), UUID.randomUUID(), "bieming"));
    }
}
