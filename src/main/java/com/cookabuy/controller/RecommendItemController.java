package com.cookabuy.controller;

import com.cookabuy.entity.service.dto.PublishRecommendItemForm;
import com.cookabuy.entity.service.dto.RecommendItemDTO;
import com.cookabuy.entity.service.dto.RecommendItemEntity;
import com.cookabuy.entity.service.po.*;
import com.cookabuy.repository.service.*;
import com.cookabuy.repository.service.specification.ActiveItemRepository;
import com.cookabuy.spring.aop.annotation.MenuItem;
import com.cookabuy.spring.aop.annotation.RequiresPermission;
import com.cookabuy.thirdParty.dozer.DozerHelper;
import com.cookabuy.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author yejinbiao
 * @create 2016-12-29-11:44
 */
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
    private ActiveItemRepository activeItemRepository;

    @Autowired
    private PublishLogRepository publishLogRepository;

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
        int maxPosition = recommendItemRepository.findMaxPositionByPageNameAndLocation(data.getPageName(), data.getLocation());
        for (Iterator<RecommendItem> it = recommendItems.iterator(); it.hasNext(); ) {
            RecommendItem recommend = it.next();
            RecommendItem old = recommendItemRepository.findByPageNameAndLocationAndItemId(data.getPageName(), data.getLocation(), recommend.getItemId());
            if (old != null) {
                it.remove();
                continue;   //同一模块下有相同的商品则跳过，同一模块指同一pageName 和同一location
            }
            recommend.setLocation(data.getLocation());
            recommend.setPageName(data.getPageName());
            recommend.setInsertedAt(new Date());
            recommend.setUpdatedAt(new Date());
//            recommend.setPosition(++maxPosition);
            recommendItemRepository.save(recommend);

        }
        //todo 代码冗余
        List<RecommendItemDTO> dtos = dozerHelper.mapList(recommendItems, RecommendItemDTO.class);
        dtos.stream().forEach(dto -> {
            Item item = itemRepository.findByNumIid(dto.getItemId());
            //todo 如果商品不存在 ，则表示该商品失效，应设置flag
            if (item != null) {
                dto.setTitle(item.getTitle());
                dto.setPrice(item.getPrice());
                Optional<Store> store = Optional.ofNullable(item.getStore());
                store.ifPresent(value -> {
                    dto.setShopName(value.getStoreName());
                    dto.setMarket(value.getMarket());
                });
                if (StringUtils.isEmpty(dto.getPicUrl())) {
                    dto.setPicUrl(item.getPicUrl());
                }
            }
        });
        return new Result("items", dtos);// 返回添加的items

    }

    @RequestMapping("list_items")
    @MenuItem
    public Result listItems(String pageName, String location, Result result) {
        List<RecommendItem> recommendItems = recommendItemRepository.findByPageNameAndLocationOrderByWeightDesc(pageName, location);
        List<RecommendItemDTO> dtos = dozerHelper.mapList(recommendItems, RecommendItemDTO.class);
        dtos.stream().forEach(dto -> {
            Item item = itemRepository.findByNumIid(dto.getItemId());
            //todo 如果商品不存在 或者下架，则表示该商品失效，应设置flag
            if (item != null) {
                dto.setTitle(item.getTitle());
                dto.setPrice(item.getPrice());
                Optional<Store> store = Optional.ofNullable(item.getStore());
                store.ifPresent(value -> {
                    dto.setShopName(value.getStoreName());
                    dto.setMarket(value.getMarket());
                });
                if (StringUtils.isEmpty(dto.getPicUrl())) {
                    dto.setPicUrl(item.getPicUrl());
                }
            }
        });

        List<Ad> ads = adRepository.findByPageNameAndLocation(pageName, location);

        List<RecommendCategory> categories = recommendCategoryRepository.findByPageNameOrderByOrder(pageName);

        result.addData("picUrl", CollectionUtils.isEmpty(ads) ? null : ads.get(0).getPicUrl());
        result.addData("stores", dtos);
        result.addData("categories", categories);
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
        List<RecommendItem> itemsToBeSaved = new ArrayList<>();
        form.getWeights().stream().forEach(itemWeight -> {
           RecommendItem item = recommendItemRepository.findOne(itemWeight.getId());
           item.setWeight(itemWeight.getWeight());
           itemsToBeSaved.add(item);
        });

        recommendItemRepository.save(itemsToBeSaved);
        activeItemRepository.deleteAll();
        List<ActiveItem> itemsToBePublished = new ArrayList<>();
        recommendItemRepository.findByPageNameAndLocationOrderByWeightDesc(form.getPage(),form.getLocation())
                .stream().limit(10).forEach(item -> {
                    ActiveItem activeItem = new ActiveItem(item.getItemId(), item.getLocation(), item.getPageName()
                    , item.getPicUrl(), item.getWeight());
                    itemsToBePublished.add(activeItem);
        });
        activeItemRepository.save(itemsToBePublished);
        publishLogRepository.save(new PublishLog("item", new Date()));
        return new Result();
    }
}
