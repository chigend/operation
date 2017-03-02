package com.cookabuy.controller;

import com.cookabuy.entity.service.dto.*;
import com.cookabuy.entity.service.po.*;
import com.cookabuy.repository.service.*;
import com.cookabuy.service.AdService;
import com.cookabuy.service.UpdateService;
import com.cookabuy.spring.aop.annotation.MenuItem;
import com.cookabuy.spring.aop.annotation.RequiresPermission;
import com.cookabuy.thirdParty.cos.FileHelper;
import com.cookabuy.thirdParty.dozer.DozerHelper;
import com.cookabuy.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static com.cookabuy.constant.CosConstant.BUCKET;
import static com.cookabuy.constant.CosConstant.DIRECTORY_PREFIX_AD_PATH;
import static com.cookabuy.constant.ErrorConstant.UPLOAD_IMAGE_FAIL;
import static com.cookabuy.constant.LocationConstant.TOP_BLOCK;
import static com.cookabuy.constant.PageContant.HOT;
import static com.cookabuy.constant.PublishType.HOT_AD;

/**
 * @author yejinbiao
 * @create 2016-12-29-11:44
 */
@Slf4j
@RestController
@RequestMapping("/operate")
public class RecommendItemController {
    @Autowired
    private DozerHelper dozerHelper;

    @Autowired
    private RecommendItemRepository recommendItemRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private RecommendCategoryRepository recommendCategoryRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private PublishedItemRepository activeItemRepository;

    @Autowired
    private PublishLogRepository publishLogRepository;

    @Autowired
    private DozerBeanMapper mapper;

    @Autowired
    private AdService adService;

    @Autowired
    private FileHelper fileHelper;

    @Autowired
    private UpdateService updateService;
    /**
     * @RequiresPermission   注意此注解并非shiro的@RequiresPermissions,由于爆款专区，热销类目，商品管理模块的推荐
     * 功能一样，只是参数不同，故不能将权限粒度控制在以上3个页面上的每个操作上（即如果使用shiro的注解，以上3个页面三个页面
     * 的操作权限是同步的，要么都能进入，要么都不能进入方法,故通过spring aop实现自己的逻辑）
     * @see com.cookabuy.spring.aop.aspect.PermissionAspect#checkPermission(Object)
     * @see RequiresPermission
     * @param data
     * @return
     */
    @RequestMapping("/recommend_item")
    @RequiresPermission
    public Result recommendItem(@RequestBody RecommendItemEntity data) {
        //todo 修改该参数类名
        List<RecommendItem> recommendItems = dozerHelper.mapList(data.getItems(), RecommendItem.class);
        for (Iterator<RecommendItem> it = recommendItems.iterator(); it.hasNext(); ) {
            RecommendItem recommend = it.next();
            RecommendItem old = recommendItemRepository.findByPageNameAndLocationAndItemId(data.getPageName(), data.getLocation(), recommend.getItemId());
            if (old != null) {
                it.remove();
                continue;   //同一模块下有相同的商品则跳过，同一模块指同一pageName 和同一location
            }
            recommend.setLocation(data.getLocation());
            recommend.setPageName(data.getPageName());
            recommend.setModifyTime(new Date());
            recommend.setDeleted(false);
            recommendItemRepository.save(recommend);

        }
        //todo 代码冗余
        List<RecommendItemDTO> dtos = dozerHelper.mapList(recommendItems, RecommendItemDTO.class);
        dtos.stream().forEach(dto -> {
            Item item = itemRepository.findOne(dto.getItemId());
            //todo 如果商品不存在 ，则表示该商品失效，应设置flag
            if (item != null) {
                dto.setTitle(item.getTitle());
                dto.setPrice(item.getPrice());
                Optional<Store> store = Optional.ofNullable(item.getStore());
                store.ifPresent(value -> {
                    dto.setShopName(value.getStoreName());
                    dto.setMarket(value.getMarket());
                    dto.setStall(resolveStoreLocation(value.getLocation()));
                });
                if (StringUtils.isEmpty(dto.getPicUrl())) {
                    dto.setPicUrl(item.getPicUrl());
                }
            }
        });

        return new Result("items",dtos);
    }

    @RequestMapping("list_items")
    @MenuItem
    public Result listItems(String pageName, String location, Result result) {
        List<RecommendItem> recommendItems = recommendItemRepository.findByPageNameAndLocationOrderByWeightDesc(pageName, location);
        List<RecommendItemDTO> dtos = dozerHelper.mapList(recommendItems, RecommendItemDTO.class);
        dtos.stream().forEach(dto -> {
            Item item = itemRepository.findOne(dto.getItemId());
            //todo 如果商品不存在 或者下架，则表示该商品失效，应设置flag
            if (item != null) {
                dto.setTitle(item.getTitle());
                dto.setPrice(item.getPrice());
                Optional<Store> store = Optional.ofNullable(item.getStore());
                store.ifPresent(value -> {
                    dto.setShopName(value.getStoreName());
                    dto.setMarket(value.getMarket());
                    dto.setStall(resolveStoreLocation(value.getLocation()));
                });
            }
        });
        //商品数据
        result.addData("stores", dtos);

        //爆款专区男装和女装区域的广告图
        List<Ad> ads = adRepository.findByPageNameAndLocation(pageName, location);
        result.addData("ad", CollectionUtils.isEmpty(ads) ? null : mapper.map(ads.get(0), DisPlayAd.class));

        //下拉框可选类目
        List<RecommendCategory> categories = recommendCategoryRepository.findByPageNameOrderByOrder(pageName);
        result.addData("categories", categories);

        //发布按钮是否激活
        boolean activate = recommendItemRepository.publishActivate(pageName,location,getPublishType(pageName,location));
        result.addData("activate", activate);

        return result;
    }

    @RequestMapping("delete_item")
    @RequiresPermissions("recommendItem:delete")
    public Result deleteItems(@RequestBody List<UUID> ids) {
        recommendItemRepository.deleteRecommendItemWithIds(ids);

        return new Result();
    }

    @RequestMapping("publish_items")
    public Result publishItems(@RequestBody PublishRecommendItemForm form) {
        //先保存权重信息
        List<RecommendItem> itemsToBeSaved = new ArrayList<>();
        form.getWeights().stream().forEach(itemWeight -> {
           RecommendItem item = recommendItemRepository.findOne(itemWeight.getId());
           item.setWeight(itemWeight.getWeight());
           itemsToBeSaved.add(item);
        });
        recommendItemRepository.save(itemsToBeSaved);

        //按照模块（不同pageName和location）来清除原先发布的内容
        String pageName = form.getPage();
        String location = form.getLocation();
        activeItemRepository.deleteByPageNameAndLocation(pageName, location);
        //重新发布新的内容
        List<PublishedItem> itemsToBePublished = new ArrayList<>();
        recommendItemRepository.findByPageNameAndLocationOrderByWeightDesc(pageName,location)
                .stream().limit(10).forEach(item -> {
                    PublishedItem activeItem = new PublishedItem(item.getItemId(), item.getLocation(), item.getPageName()
                    , item.getPicUrl(), item.getWeight());
                    itemsToBePublished.add(activeItem);
        });
        activeItemRepository.save(itemsToBePublished);

        //并要写一条发布记录
        publishLogRepository.save(new PublishLog(getPublishType(pageName, location), new Date()));

        //男装爆款和女装爆款有广告图可以发布 今日爆款无广告图
        if (!TOP_BLOCK.equals(location)) {
            adService.publishAds(HOT, location, HOT_AD);
        }

        return new Result();
    }

    //
    @RequestMapping("upload_ad")
    public Result uploadAd(AddAdForm form , Result result){
        String picUrl = fileHelper.uploadFile(BUCKET, DIRECTORY_PREFIX_AD_PATH, form.getImage());
        if(picUrl == null){
            result.setError(UPLOAD_IMAGE_FAIL);
            return result;
        }
        log.info("upload file successfully,source_url is {}",picUrl);
        String pageName = form.getPageName();
        Ad ad = mapper.map(form,Ad.class);
        ad.setPicUrl(picUrl);
        ad.setPageName(pageName);
        ad.setLocation(form.getLocation());
        ad.setModifyTime(new Date());
        ad.setHidden(false);
        adRepository.save(ad);
        return new Result("ad", mapper.map(ad, DisPlayAd.class));
    }


    /**
     * 处理档口号，由于数据库中存储的档口号前拼接了市场名称，所以需要截取
     * @param location  截取前的档口号  如：新百佳 - 大门口A - A11022
     * @return  截取后的档口号 如：大门口A - A11022
     */
    private String resolveStoreLocation(String location) {
        if (location == null) {
            return location;
        }
        int index = location.indexOf("-");
        return location.substring(index + 1);
    }

    private String getPublishType(String pageName, String location) {
        return pageName.concat(location);
    }

}
