package test;

import com.cookabuy.constant.PageContant;
import com.cookabuy.entity.service.po.Ad;
import com.cookabuy.repository.service.AdRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

/**
 * @author yejinbiao
 * @create 2016-12-26-10:31
 */

public class TestAdRepository extends AbstractJpaTest{
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
        boolean activate = adRepository.publishActicate();
        System.out.println(activate);
    }
    @Test
    public void testDelete() {
        adRepository.logicDelete(Arrays.asList(4,7));
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

    }
}
