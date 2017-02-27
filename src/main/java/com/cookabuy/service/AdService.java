package com.cookabuy.service;

import com.cookabuy.constant.PublishType;
import com.cookabuy.entity.service.po.PublishedAd;
import com.cookabuy.entity.service.po.Ad;
import com.cookabuy.entity.service.po.PublishLog;
import com.cookabuy.repository.service.PublishedAdRepository;
import com.cookabuy.repository.service.AdRepository;
import com.cookabuy.repository.service.PublishLogRepository;
import com.cookabuy.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;

import static com.cookabuy.constant.PageContant.INDEX;

/**
 * @author yejinbiao
 * @create 2017-02-13-下午3:09
 */
@Service
public class AdService {
    @Autowired
    private AdRepository adRepository;
    @Autowired
    private PublishedAdRepository activeAdRepository;
    @Autowired
    private PublishLogRepository publishLogRepository;

    @Transactional(value = "serviceTransactionManager", rollbackFor = Exception.class)
    public void publishAds() {
        //清空所有已发布的广告
        activeAdRepository.deleteAll();
        //重新添加所有启用的广告
        adRepository.findByPageNameOrderByPositionAsc(INDEX).stream().filter(ad -> !ad.isHidden())
                .forEach(ad -> {
                    PublishedAd aa = new PublishedAd(ad.getActivityUrl(), ad.getPageName(), ad.getPicUrl(), ad.getPosition(), ad.getTip());
                    activeAdRepository.save(aa);
                });
        //todo operator
        publishLogRepository.save(new PublishLog(PublishType.AD, new Date()));

    }

    @Transactional(value = "serviceTransactionManager", rollbackFor = Exception.class)
    public Result moveAd(Ad ad, Ad ad2) {
        Integer temp = ad.getPosition();
        ad.setPosition(ad2.getPosition());
        ad2.setPosition(temp);
        adRepository.save(Arrays.asList(ad,ad2));
        return new Result();
    }

}
