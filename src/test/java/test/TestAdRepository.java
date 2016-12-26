package test;

import com.cookabuy.entity.service.po.Ad;
import com.cookabuy.repository.service.AdRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
}
