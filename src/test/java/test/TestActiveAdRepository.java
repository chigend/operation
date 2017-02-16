package test;

import com.cookabuy.entity.service.po.ActiveAd;
import com.cookabuy.repository.service.ActiveAdRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * @author yejinbiao
 * @create 2017-02-15-下午2:58
 */

public class TestActiveAdRepository extends  AbstractJpaTest {
    @Autowired
    private ActiveAdRepository activeAdRepository;
    @Test
    public void testActiveAd() {
        ActiveAd ad = new ActiveAd();
        ad.setAdId(UUID.randomUUID());
        activeAdRepository.save(ad);
    }
    @Test
    public void testFindActiveAd() {
        ActiveAd ad = activeAdRepository.findOne(UUID.fromString("aaa8d98d-4b02-439b-a34e-370013abd547"));
        System.out.println(ad.getAdId());
    }
}
