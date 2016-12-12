package test;

//import com.cookabuy.repository.service.RecommendRepository;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cookabuy.entity.service.po.Recommend;
import com.cookabuy.repository.service.RecommendRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.cookabuy.repository.service.specification.RecommendSpecifications.*;

/**
 * 2016/12/5
 */
public class TestRecommendRepository extends AbstractJpaTest {
    @Autowired
    private RecommendRepository recommendRepository;
//    @Test
//    public void test(){
//        recommendRepository.findAll().stream().limit(10).forEach(System.out::println);
//    }

    @Test
    public void findByRecommends(){
        Recommend recommend = new Recommend();
        recommend.setLocation("left");
        recommend.setPageName("index");
        recommend.setPosition(1);
        recommendRepository.findAll(findByRecommend(recommend)).stream().map(JSONObject::toJSON).forEach(System.out::println);

    }

    @Test

    public void testFindOneBySpecification(){
        Recommend recommend = new Recommend();
        recommend.setLocation("left");
        recommend.setPageName("index");
        recommend.setPosition(1);
        recommend = recommendRepository.findOne(findByRecommend(recommend));
        System.out.print("--------------");
        System.out.print(JSON.toJSONString(recommend));
        System.out.print("--------------");

    }
//    @Test
//    public void testFindOneOptional(){
//        Recommend recommend = new Recommend();
//        recommend.setLocation("left");
//        recommend.setPageName("index");
//        recommend.setPosition(1);
//        Optional<Recommend> option = recommendRepositoryfindOneOptional(findByRecommend(recommend));
//        option.ifPresent(value->{
//            System.out.print(JSON.toJSON(value));
//        });
//    }
}
