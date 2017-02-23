package test;

//import com.cookabuy.repository.service.RecommendRepository;
import com.alibaba.fastjson.JSON;
import com.cookabuy.entity.service.po.ActiveItem;
import com.cookabuy.entity.service.po.Item;
import com.cookabuy.entity.service.po.RecommendItem;
import com.cookabuy.repository.service.ActiveItemRepository;
import com.cookabuy.repository.service.ItemRepository;
import com.cookabuy.repository.service.RecommendItemRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 2016/12/5
 */
public class TestRecommendRepository extends AbstractJpaTest {
    @Autowired
    private RecommendItemRepository recommendRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ActiveItemRepository activeItemRepository;

//    @Autowired
//    private Item
//    @Test
//    public void test(){
//        recommendRepository.findAll().stream().limit(10).forEach(System.out::println);
//    }

    @Test
    public void findByRecommends(){
        RecommendItem recommend = new RecommendItem();
        recommend.setLocation("left");
        recommend.setPageName("index");

    }

    @Test

    public void testFindOneBySpecification(){
        RecommendItem recommend = new RecommendItem();
        recommend.setLocation("left");
        recommend.setPageName("index");
        System.out.print("--------------");
        System.out.print(JSON.toJSONString(recommend));
        System.out.print("--------------");

    }
    @Test
    public void testaddRecommend(){
        itemRepository.findTopAndOffset(162,0).stream().filter(item -> item.getTitle().contains("女装")).limit(20).map(Item::getId).forEach(id->{
            RecommendItem recommend = new RecommendItem();
            recommend.setItemId(id);
            recommend.setLocation("girl");
            recommend.setPageName("index");
            recommend.setCreateTime(new Date());
            recommend.setModifyTime(new Date());
            recommendRepository.save(recommend);
        });
    }
    @Test
    public void testasdsa(){
        itemRepository.findTopAndOffset(1000,1).stream().distinct().limit(20).forEach(item -> {
        });
    }

    @Test
    public void testaddRecommend2() {
        itemRepository.findAll().stream().filter(item -> item.getTitle().contains("男生")).limit(20).map(Item::getId).forEach(id -> {
            System.out.println(id);
            RecommendItem recommend = new RecommendItem();
            recommend.setItemId(id);
            recommend.setLocation("boy");
            recommend.setPageName("index");
            recommend.setCreateTime(new Date());
            recommend.setModifyTime(new Date());
            recommendRepository.save(recommend);
        });
    }
    @Test
    public void testTitle(){
        itemRepository.findByTitleLike("%热销%").stream().filter(item -> !item.getTitle().contains("情侣")).limit(4).map(Item::getId).forEach(id -> {
            System.out.println(id);
            RecommendItem recommend = new RecommendItem();
            recommend.setItemId(id);
            recommend.setLocation("right");
            recommend.setPageName("index");
            recommend.setCreateTime(new Date());
            recommend.setModifyTime(new Date());
            recommendRepository.save(recommend);
        });
    }
    @Test
    public void testAddActiveItem() {
        itemRepository.findByTitleLike("%爆款%").stream().filter(item -> !item.getTitle().contains("情侣")).limit(8).map(Item::getId).forEach(id -> {
            System.out.println(id);
            ActiveItem recommend = new ActiveItem();
            recommend.setItemId(id);
            recommend.setLocation("left");
            recommend.setPageName("index");
            activeItemRepository.save(recommend);
        });
    }

    @Test
    public void testDeleteByInCollection(){
        List<Integer> ids = Arrays.asList(311, 310);
    }



    @Test
    public void testFindByPageNameAndLocationAndItemId () {
        RecommendItem recommend = new RecommendItem();
        recommend.setLocation("hello");
        System.out.println(recommend);
        recommend = recommendRepository.findByPageNameAndLocationAndItemId("index", "hello", UUID.fromString("cf9fac09-6579-4d8a-89e5-f1c4fd414701"));
        System.out.println(recommend);

    }
}
