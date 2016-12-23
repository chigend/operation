package com.cookabuy.controller;

import com.cookabuy.constant.AdPageName;
import com.cookabuy.entity.service.dto.AddAdForm;
import com.cookabuy.entity.service.dto.DisPlayAd;
import com.cookabuy.entity.service.po.Ad;
import com.cookabuy.repository.service.AdRepository;
import com.cookabuy.util.DozerHelper;
import com.cookabuy.util.Result;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author yejinbiao
 * @create 2016-12-23-11:30
 */
@RestController
@RequestMapping("operate")
public class AdController {
    @Autowired
    private AdRepository adRepository;
    @Autowired
    private DozerHelper dozerHelper;
    @Autowired
    private DozerBeanMapper dozerBeanMapper;
    @RequestMapping("ads")
    public Result findIndexAds(Result result){
        List<Ad> ads = adRepository.findByPageNameOrderByPositionAsc(AdPageName.INDEX);

        List<DisPlayAd> disPlayAds = dozerHelper.mapList(ads,DisPlayAd.class);
        result.addData("ads",disPlayAds);
        return  result;
    }

    @RequestMapping("add_ad")
    public Result addAd(AddAdForm form,Result result){
        Ad ad = dozerBeanMapper.map(form,Ad.class);
        ad.setCreateTime(new Date());
        ad.setPageName(AdPageName.INDEX);
        adRepository.save(ad);
        return result;
    }

}
