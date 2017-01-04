package com.cookabuy.controller;

import com.cookabuy.entity.service.dto.AddAdForm;
import com.cookabuy.entity.service.dto.DisPlayAd;
import com.cookabuy.entity.service.dto.SaveAdForm;
import com.cookabuy.entity.service.dto.UpdateAdForm;
import com.cookabuy.entity.service.po.Ad;
import com.cookabuy.repository.service.AdRepository;
import com.cookabuy.spring.aop.annotation.MenuItem;
import com.cookabuy.thirdParty.cos.FileHelper;
import com.cookabuy.thirdParty.dozer.DozerHelper;
import com.cookabuy.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.util.CollectionUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

import static com.cookabuy.constant.CosConstant.BUCKET;
import static com.cookabuy.constant.CosConstant.DIRECTORY_PREFIX_AD_PATH;
import static com.cookabuy.constant.ErrorConstant.NOT_ASSIGN_ADS;
import static com.cookabuy.constant.ErrorConstant.UPLOAD_IMAGE_FAIL;
import static com.cookabuy.constant.PageContant.INDEX;

/**
 * @author yejinbiao
 * @create 2016-12-23-11:30
 */
@RestController
@RequestMapping("operate")
@Slf4j
public class AdController {
    @Autowired
    private AdRepository adRepository;

    @Autowired
    private DozerHelper dozerHelper;

    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    @Autowired
    private FileHelper fileHelper;

    @RequestMapping("ads")
    @MenuItem
    public Result findIndexAds(Result result){
        List<Ad> ads = adRepository.findByPageNameOrderByPositionAsc(INDEX);

        List<DisPlayAd> disPlayAds = dozerHelper.mapList(ads,DisPlayAd.class);
        result.addData("ads",disPlayAds);
        return  result;
    }

    @RequestMapping("add_ad")
    public Result addAd(AddAdForm form , Result result){
        String picUrl = fileHelper.uploadFile(BUCKET, DIRECTORY_PREFIX_AD_PATH, form.getImage());
        if(picUrl == null){
            result.setError(UPLOAD_IMAGE_FAIL);
            return result;
        }
        log.info("upload file successfully,source_url is {}",picUrl);
        Ad ad = dozerBeanMapper.map(form,Ad.class);
        ad.setPicUrl(picUrl);
        ad.setPageName(INDEX);
        ad.setCreateTime(new Date());
        adRepository.save(ad);
        return result;
    }


    @RequestMapping("delete_ad")
    public Result deleteAd(@RequestBody List<Integer> ids){
        if(CollectionUtils.isEmpty(ids)){
            return new Result(NOT_ASSIGN_ADS);
        }
        ids.stream().forEach(id->{
            Ad ad = adRepository.findOne(id);
            //删除广告之前把存储在cos的图片进行删除
            fileHelper.deleteFile(BUCKET, ad.getPicUrl());
            adRepository.delete(ad);
        });
        return new Result();
    }
    //根据adId 来更新每一个广告的position
    @RequestMapping("save_ad")
    public Result save(@RequestBody List<SaveAdForm> list){
        if(CollectionUtils.isEmpty(list)){
            return new Result(NOT_ASSIGN_ADS);
        }
        list.stream().filter(ad-> ad.getAdId()!=null && ad.getPosition()!=null).forEach(ad->
            adRepository.updatePositionById(ad.getAdId(),ad.getPosition()));
        return new Result();
    }

    @RequestMapping("update_ad")
    public Result update(UpdateAdForm form,Result result){
        Ad ad = adRepository.findOne(form.getAdId());
        if(form.getImage() != null){
//          如果广告图片修改过，那么把之前把存储在cos的图片进行删除
//            fileHelper.deleteFile(CosConstant.BUCKET,ad.getPicUrl());
           String picUrl = fileHelper.uploadFile(BUCKET, DIRECTORY_PREFIX_AD_PATH,form.getImage());
           if (picUrl != null){
               fileHelper.deleteFile(BUCKET, ad.getPicUrl());
               ad.setPicUrl(picUrl);
           }
        }
        String activityUrl = form.getActivityUrl();
        if(activityUrl != null){
            ad.setActivityUrl(activityUrl);
        }
        adRepository.save(ad);
        return new Result();
    }

    @RequestMapping("toggle_hidden")
    public Result toggle(Integer adId,Result result){

        adRepository.toggleHiddenByAdId(adId);
        return result;
    }

    //用于爆款专区的广告图上传
    @RequestMapping("/upload_ad_img")
    public Result uploadAdImg(AddAdForm addAdForm) {
        String url = fileHelper.uploadFile(BUCKET, DIRECTORY_PREFIX_AD_PATH, addAdForm.getImage());
        if (url == null) {
            return new Result(UPLOAD_IMAGE_FAIL);
        }
        //todo 删除之前的
        List<Ad> ads = adRepository.findByPageNameAndLocation(addAdForm.getPageName(), addAdForm.getLocation());
        if (CollectionUtils.isEmpty(ads)) {
            Ad ad = dozerBeanMapper.map(addAdForm, Ad.class);
            ad.setPicUrl(url);
            adRepository.save(ad);
        }else {
            Ad ad = ads.get(0);
            if (ad.getPicUrl() != null) {
                fileHelper.deleteFile(BUCKET, ad.getPicUrl());
            }
            ad.setPicUrl(url);
            adRepository.save(ad);
        }
        return new Result("picUrl", url);
    }

}
