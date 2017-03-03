package com.cookabuy.controller;

import com.cookabuy.entity.service.dto.AddAdForm;
import com.cookabuy.entity.service.dto.DisPlayAd;
import com.cookabuy.entity.service.dto.UpdateAdForm;
import com.cookabuy.entity.service.po.Ad;
import com.cookabuy.repository.service.AdRepository;
import com.cookabuy.repository.service.PublishLogRepository;
import com.cookabuy.service.AdService;
import com.cookabuy.spring.aop.annotation.MenuItem;
import com.cookabuy.thirdParty.cos.FileHelper;
import com.cookabuy.thirdParty.dozer.DozerHelper;
import com.cookabuy.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.util.CollectionUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.cookabuy.constant.CosConstant.BUCKET;
import static com.cookabuy.constant.CosConstant.DIRECTORY_PREFIX_AD_PATH;
import static com.cookabuy.constant.ErrorConstant.NOT_ASSIGN_ADS;
import static com.cookabuy.constant.ErrorConstant.UPLOAD_IMAGE_FAIL;
import static com.cookabuy.constant.PageContant.MARKET;
import static com.cookabuy.constant.PublishType.MARKET_AD;

/**
 * @author yejinbiao
 * @create 2016-12-23-11:30
 */
@RestController
@RequestMapping("market")
@Slf4j
public class MarketController {
    @Autowired
    private AdService adService;
    @Autowired
    private AdRepository adRepository;

    @Autowired
    private PublishLogRepository publishLogRepository;
    @Autowired
    private DozerHelper dozerHelper;

    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    @Autowired
    private FileHelper fileHelper;

    @RequestMapping("ads")
    @MenuItem
    public Result findMarketAds(Result result) {
        List<Ad> ads = adRepository.findByPageNameOrderByPositionAsc(MARKET);

        List<DisPlayAd> disPlayAds = dozerHelper.mapList(ads, DisPlayAd.class);
        boolean activate = adRepository.publishActivate(MARKET_AD);
        result.addData("ads", disPlayAds);
        result.addData("activate", activate);
        return result;
    }

    @RequestMapping("add_ad")
    @RequiresPermissions("market:ad:add")
    public Result addAd(AddAdForm form, Result result) {
        String picUrl = fileHelper.uploadFile(BUCKET, DIRECTORY_PREFIX_AD_PATH, form.getImage());
        if (picUrl == null) {
            result.setError(UPLOAD_IMAGE_FAIL);
            return result;
        }
        log.info("upload file successfully,source_url is {}", picUrl);
        Integer maxPosition = adRepository.findMaxPositionByPageName(MARKET);
        Ad ad = dozerBeanMapper.map(form, Ad.class);
        ad.setPicUrl(picUrl);
        ad.setPageName(MARKET);
        ad.setCreateTime(new Date());
        ad.setModifyTime(new Date());
        ad.setHidden(true);
        ad.setPosition(maxPosition + 1);
        adRepository.save(ad);
        return new Result("ad", ad);
    }


    @RequestMapping("delete_ad")
    @RequiresPermissions("market:ad:delete")
    public Result deleteAd(@RequestBody List<UUID> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return new Result(NOT_ASSIGN_ADS);
        }
        //todo  删除对应的存储在cos上的图片
        adRepository.logicDelete(ids);
        return new Result();
    }
    //根据adId 来更新每一个广告的position
//    @RequestMapping("save_ad")
//    @RequiresPermissions("ad:move")
//    public Result save(@RequestBody List<SaveAdForm> list){
//        if(CollectionUtils.isEmpty(list)){
//            return new Result(NOT_ASSIGN_ADS);
//        }
//        list.stream().filter(ad-> ad.getAdId()!=null && ad.getPosition()!=null).forEach(ad->
//                adRepository.updatePositionById(ad.getAdId(),ad.getPosition()));
//        return new Result();
//    }

    /**
     * @param adId  两个待交换位置的adId
     * @param adId2
     * @return
     */
    @RequestMapping("move_ad")
    @RequiresPermissions("market:ad:move")
    public Result move(@RequestParam UUID adId, @RequestParam UUID adId2) {
        Ad ad = adRepository.findOne(adId);
        Ad ad2 = adRepository.findOne(adId2);
        if (ad == null || ad2 == null) {
            return new Result(NOT_ASSIGN_ADS);
        }
        return adService.moveAd(ad, ad2);
    }

    @RequestMapping("update_ad")
    @RequiresPermissions("market:ad:update")
    public Result update(UpdateAdForm form, Result result) {
        Ad ad = adRepository.findOne(form.getAdId());
        if (form.getImage() != null) {
//          如果广告图片修改过，那么把之前把存储在cos的图片进行删除
//            fileHelper.deleteFile(CosConstant.BUCKET,ad.getPicUrl());
            String picUrl = fileHelper.uploadFile(BUCKET, DIRECTORY_PREFIX_AD_PATH, form.getImage());
            if (picUrl != null) {
                fileHelper.deleteFile(BUCKET, ad.getPicUrl());
                ad.setPicUrl(picUrl);
            }
        }
        String activityUrl = form.getActivityUrl();
        if (activityUrl != null) {
            ad.setActivityUrl(activityUrl);
        }
        String tip = form.getTip();
        if (tip != null) {
            ad.setTip(tip);
        }
        adRepository.save(ad);
        return new Result();
    }

    @RequestMapping("toggle_hidden")
    @RequiresPermissions("market:ad:enable")
    public Result toggle(@RequestParam UUID adId, Result result) {

        adRepository.toggleHiddenByAdId(adId);
        return result;
    }

    //用于爆款专区的广告图上传
    @RequestMapping("/upload_ad_img")
    @RequiresPermissions("recommendItem:boom:adimage:add")
    public Result uploadAdImg(AddAdForm addAdForm) {
        String url = fileHelper.uploadFile(BUCKET, DIRECTORY_PREFIX_AD_PATH, addAdForm.getImage());
        if (url == null) {
            return new Result(UPLOAD_IMAGE_FAIL);
        }
        List<Ad> ads = adRepository.findByPageNameAndLocation(addAdForm.getPageName(), addAdForm.getLocation());
        if (CollectionUtils.isEmpty(ads)) {
            Ad ad = dozerBeanMapper.map(addAdForm, Ad.class);
            ad.setPicUrl(url);
            adRepository.save(ad);
        } else {
            Ad ad = ads.get(0);
            if (ad.getPicUrl() != null) {
                fileHelper.deleteFile(BUCKET, ad.getPicUrl());
            }
            ad.setPicUrl(url);
            adRepository.save(ad);
        }
        return new Result("picUrl", url);
    }

    //用于爆款专区的广告图删除
    @RequestMapping("/delete_ad_img")
    public Result deleteAdImage(String page, String location) {
        List<Ad> ads = adRepository.findByPageNameAndLocation(page, location);
        if (CollectionUtils.isEmpty(ads)) {
            return new Result("改模块广告图已删除");
        } else {
            Ad ad = ads.get(0);
            if (ad.getPicUrl() != null) {
                fileHelper.deleteFile(BUCKET, ad.getPicUrl());
            }
            adRepository.delete(ad);
        }
        return new Result();
    }

    @RequestMapping("publish_ads")
    public Result publishAds() {
        int numPublished = adService.publishAds(MARKET, null,MARKET_AD);
        return numPublished > 0 ? new Result() : new Result("发布失败，未启用任何广告项");
    }
}
