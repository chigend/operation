package com.cookabuy.service;

import com.cookabuy.entity.service.po.Ad;
import com.cookabuy.entity.service.po.PublishLog;
import com.cookabuy.entity.service.po.PublishedAd;
import com.cookabuy.repository.service.AdRepository;
import com.cookabuy.repository.service.PublishLogRepository;
import com.cookabuy.repository.service.PublishedAdRepository;
import com.cookabuy.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author yejinbiao
 * @create 2017-02-13-下午3:09
 */
@Service
public class AdService {
    @Autowired
    private AdRepository adRepository;
    @Autowired
    private PublishedAdRepository publishedAdRepository;
    @Autowired
    private PublishLogRepository publishLogRepository;

    @Transactional(value = "serviceTransactionManager", rollbackFor = Exception.class)
    public int publishAds(String pageName,String location,String publishType) {
        List<Ad> adsToBePublished = null;
        //清空所有已发布的广告//todo
        if (location == null) {
            publishedAdRepository.deleteByPageName(pageName);
            adsToBePublished = adRepository.findByPageName(pageName);
        }else {
            publishedAdRepository.deleteByPageNameAndLocation(pageName,location);
            adsToBePublished = adRepository.findByPageNameAndLocation(pageName,location);
        }

        //重新添加所有启用的广告

        long numPublished = adsToBePublished.stream().filter(ad -> !ad.isHidden()).peek(ad -> {
            System.out.println("ad:="+ad.getPicUrl());
            PublishedAd aa = new PublishedAd(ad.getActivityUrl(), ad.getLocation(), ad.getPageName(), ad.getPicUrl(), ad.getPosition(), ad.getTip());
            publishedAdRepository.save(aa);
        }).count();
        //todo operator
        if (numPublished > 0) {
            publishLogRepository.save(new PublishLog(publishType, new Date()));
        }
        return (int)numPublished;

    }

    @Transactional(value = "serviceTransactionManager", rollbackFor = Exception.class)
    public Result moveAd(Ad ad, Ad ad2) {
        Integer temp = ad.getPosition();
        ad.setPosition(ad2.getPosition());
        ad2.setPosition(temp);
        adRepository.save(Arrays.asList(ad, ad2));
        return new Result();
    }

}
