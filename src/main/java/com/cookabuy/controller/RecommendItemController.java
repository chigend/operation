package com.cookabuy.controller;

import com.cookabuy.entity.service.dto.RecommendItemDTO;
import com.cookabuy.entity.service.dto.RecommendItemEntity;
import com.cookabuy.entity.service.po.*;
import com.cookabuy.repository.service.*;
import com.cookabuy.spring.aop.annotation.MenuItem;
import com.cookabuy.thirdParty.dozer.DozerHelper;
import com.cookabuy.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

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
    private RecommendRepository recommendRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private RecommendCategoryRepository recommendCategoryRepository;

    @Autowired
    private StoreRepository storeRepository;

    @RequestMapping("/recommend_item")
    @RequiresPermissions("recommendItem:add")
    public Result recommendItem(@RequestBody RecommendItemEntity data) {
        //todo 修改该参数类名
        List<Recommend> recommendItems = dozerHelper.mapList(data.getItems(), Recommend.class);
        int maxPosition = recommendRepository.findMaxPositionByPageNameAndLocation(data.getPageName(), data.getLocation());
//        for (Recommend recommendItem : recommendItems) {
//            Recommend recommend = recommendRepository.findByPageNameAndLocationAndItemId(data.getPageName(), data.getLocation(), recommendItem.getItemId());
//            if (recommend != null) {
//                continue;   //同一模块下有相同的商品则跳过，同一模块指同一pageName 和同一location
//            }
//            recommendItem.setLocation(data.getLocation());
//            recommendItem.setPageName(data.getPageName());
//            recommendItem.setInsertedAt(new Date());
//            recommendItem.setUpdatedAt(new Date());
//            recommendItem.setPosition(++maxPosition);
//            recommendRepository.save(recommendItem);
//
//        }
//        return new Result();
        for (Iterator<Recommend> it = recommendItems.iterator(); it.hasNext(); ) {
            Recommend recommend = it.next();
            Recommend old = recommendRepository.findByPageNameAndLocationAndItemId(data.getPageName(), data.getLocation(), recommend.getItemId());
            if (old != null) {
                it.remove();
                continue;   //同一模块下有相同的商品则跳过，同一模块指同一pageName 和同一location
            }
            recommend.setLocation(data.getLocation());
            recommend.setPageName(data.getPageName());
            recommend.setInsertedAt(new Date());
            recommend.setUpdatedAt(new Date());
            recommend.setPosition(++maxPosition);
            recommendRepository.save(recommend);

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
        List<Recommend> recommendItems = recommendRepository.findByPageNameAndLocationOrderByPositionAsc(pageName, location);
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
                });
                if (StringUtils.isEmpty(dto.getPicUrl())) {
                    dto.setPicUrl(item.getPicUrl());
                }
            }
        });

        List<Ad> ads = adRepository.findByPageNameAndLocation(pageName, location);

        List<RecommendCategory> categories = recommendCategoryRepository.findByPageName(pageName);

        result.addData("picUrl", CollectionUtils.isEmpty(ads) ? null : ads.get(0).getPicUrl());
        result.addData("stores", dtos);
        result.addData("categories", categories);
        return result;
    }

    @RequestMapping("delete_item")
    @RequiresPermissions("recommendItem:delete")
    public Result deleteItems(@RequestBody List<Integer> ids) {
        recommendRepository.deleteRecommendWithIds(ids);
        return new Result();
    }
}
