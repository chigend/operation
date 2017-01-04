package test;

//import com.cookabuy.repository.service.RecommendRepository;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cookabuy.entity.service.po.Item;
import com.cookabuy.entity.service.po.Recommend;
import com.cookabuy.repository.service.ItemRepository;
import com.cookabuy.repository.service.RecommendRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.cookabuy.repository.service.specification.RecommendSpecifications.findByRecommend;

/**
 * 2016/12/5
 */
public class TestRecommendRepository extends AbstractJpaTest {
    @Autowired
    private RecommendRepository recommendRepository;
    @Autowired
    private ItemRepository itemRepository;

//    @Autowired
//    private Item
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
    @Test
    public void testaddRecommend(){
        itemRepository.findTopAndOffset(100,500).stream().filter(item -> item.getTitle().contains("女装")).limit(20).map(Item::getNumIid).forEach(numiid->{
            Recommend recommend = new Recommend();
            recommend.setItemId(numiid);
            recommend.setLocation("girl");
            recommend.setPageName("index");
            recommend.setInsertedAt(new Date());
            recommend.setUpdatedAt(new Date());
            recommendRepository.save(recommend);
        });
    }
    @Test
    public void testasdsa(){
        itemRepository.findTopAndOffset(1000,1).stream().distinct().limit(20).forEach(item -> {
            System.out.println(item.getStore().getStoreName());
        });
    }

    @Test
    public void testTitle(){
        itemRepository.findByTitleLike("%女装%").stream().filter(item -> !item.getTitle().contains("情侣")).limit(6).map(Item::getNumIid).forEach(numiid -> {
            Recommend recommend = new Recommend();
            recommend.setItemId(numiid);
            recommend.setLocation("girl");
            recommend.setPageName("hot");
            recommend.setInsertedAt(new Date());
            recommend.setUpdatedAt(new Date());
            recommendRepository.save(recommend);
        });
    }

    @Test
    public void testDeleteByInCollection(){
        List<Integer> ids = Arrays.asList(311, 310);
    }



    @Test
    public void testFindByPageNameAndLocationAndItemId () {
        Recommend recommend = new Recommend();
        recommend.setLocation("hello");
        System.out.println(recommend);
        recommend = recommendRepository.findByPageNameAndLocationAndItemId("index", "hello", 543130633958L);
        System.out.println(recommend);

    }
}
