package test;

import com.cookabuy.constant.PageContant;
import com.cookabuy.entity.service.po.Ad;
import com.cookabuy.repository.service.AdRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

/**
 * @author yejinbiao
 * @create 2016-12-26-10:31
 */

public class TestAdRepository extends AbstractServiceJpaTest{
    @Autowired
    private AdRepository adRepository;

    @Test
    public void test(){
        Ad ad = new Ad();
        ad.setPageName("index");
        ad.setPosition(3);
        adRepository.save(ad);
    }
    @Test
    public void testActivate(){
        boolean activate = adRepository.publishActivate();
        System.out.println(activate);
    }
    @Test
    public void testDelete() {
    }
    @Test
    public void testListads() {
        adRepository.findByPageNameOrderByPositionAsc(
                PageContant.INDEX).stream().forEach(ad->{
                    System.out.println(ad.getPicUrl());
        });
    }
    @Test
    public void testToggleAdHidden() {
        adRepository.toggleHiddenByAdId(UUID.fromString("b76cfbbd-e8f9-443a-ba70-da7ceb03175e"));
    }
    @Test
    public void testFindMaxPosition() {
        Integer max = adRepository.findMaxPositionByPageName(PageContant.INDEX);
        System.out.println(max);
    }
    @Test
    public void testFindByPageAndName() {
        List<Ad> ads = adRepository.findByPageNameAndLocation("hot", "boy");
        ads.stream().forEach(ad -> System.out.println(ad.getPicUrl()));
    }
}
