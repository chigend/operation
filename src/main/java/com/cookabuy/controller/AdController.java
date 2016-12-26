package com.cookabuy.controller;

import com.cookabuy.constant.AdPageName;
import com.cookabuy.constant.CosConstant;
import com.cookabuy.entity.service.dto.AddAdForm;
import com.cookabuy.entity.service.dto.DisPlayAd;
import com.cookabuy.entity.service.po.Ad;
import com.cookabuy.repository.service.AdRepository;
import com.cookabuy.thirdParty.cos.FileHelper;
import com.cookabuy.thirdParty.dozer.DozerHelper;
import com.cookabuy.util.Result;
import org.apache.shiro.util.CollectionUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private FileHelper fileHelper;

    @RequestMapping("ads")
    public Result findIndexAds(Result result){
        List<Ad> ads = adRepository.findByPageNameOrderByPositionAsc(AdPageName.INDEX);

        List<DisPlayAd> disPlayAds = dozerHelper.mapList(ads,DisPlayAd.class);
        result.addData("ads",disPlayAds);
        return  result;
    }

    @RequestMapping("add_ad")
    public Result addAd(AddAdForm form , Result result){
        String picUrl = fileHelper.uploadFile(CosConstant.BUCKET,CosConstant.DIRECTORY_PREFIX_AD_PATH,form.getImage());
        if(picUrl == null){
            result.setError("图片上传失败");
            return result;
        }
        Ad ad = dozerBeanMapper.map(form,Ad.class);
        ad.setPicUrl(picUrl);
        ad.setPageName(AdPageName.INDEX);
        ad.setCreateTime(new Date());
        adRepository.save(ad);
        return result;
    }


    @RequestMapping("delete_ad")
    public Result deleteAd(@RequestBody List<Integer> ids){
        if(CollectionUtils.isEmpty(ids)){
            return new Result("未指定要删除的广告轮播图");
        }
        ids.stream().forEach(adRepository::delete);
        return new Result();
    }

}
