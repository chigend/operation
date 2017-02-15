package test;

import com.alibaba.fastjson.JSON;
import com.cookabuy.repository.service.RecommendStoreRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yejinbiao
 * @create 2016-12-28-13:42
 */

public class TestRecommendStoreRepository extends AbstractJpaTest {
    @Autowired
    private RecommendStoreRepository recommendStoreRepository;
    @Test
    public void test(){
        String json = JSON.toJSONString(recommendStoreRepository.findAll());
        System.out.println(json);
    }
    @Test
    public void testExistStoreId() {
        boolean flag = recommendStoreRepository.exists(1);
        System.out.println(flag);
    }
}
