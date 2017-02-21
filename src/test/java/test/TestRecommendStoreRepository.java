package test;

import com.alibaba.fastjson.JSON;
import com.cookabuy.entity.service.po.RecommendStore;
import com.cookabuy.repository.service.RecommendStoreRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static com.cookabuy.constant.PageContant.INDEX;

/**
 * @author yejinbiao
 * @create 2016-12-28-13:42
 */

public class TestRecommendStoreRepository extends AbstractServiceJpaTest {
    @Autowired
    private RecommendStoreRepository recommendStoreRepository;
    @Test
    public void test(){
        String json = JSON.toJSONString(recommendStoreRepository.findAll());
        System.out.println(json);
    }
    @Test
    public void testExistId() {
        boolean flag = recommendStoreRepository.exists(UUID.fromString(""));
        System.out.println(flag);
    }
    @Test
    public void testExistStoreId() {
        boolean flag = recommendStoreRepository.existRecommendStore(UUID.fromString("ea11fa56-9fdf-461f-8e44-3a3691da6853"));
        System.out.println(flag);
    }

    @Test
    public void testFindByPageAndPosition() {
        RecommendStore store = recommendStoreRepository.findByPageAndPosition(INDEX, 2);
        System.out.println(store.getStoreId());
    }
}
