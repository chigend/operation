package test;

import com.cookabuy.entity.service.po.PublishedAd;
import com.cookabuy.repository.service.PublishedAdRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * @author yejinbiao
 * @create 2017-02-15-下午2:58
 */

public class TestActiveAdRepository extends  AbstractJpaTest {
    @Autowired
    private PublishedAdRepository activeAdRepository;
    @Test
    public void testFindActiveAd() {
        PublishedAd ad = activeAdRepository.findOne(UUID.fromString("aaa8d98d-4b02-439b-a34e-370013abd547"));
        System.out.println(ad.getActivityUrl());
    }
}
