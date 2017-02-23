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
    @Test
    public void testFindName() {
        categoryLinkRepository.findAliasByDisplayId(UUID.fromString("bb2d5383-2f2e-449e-bc83-0975123de47c"))
                .stream().forEach(System.out::println);
    }
}
