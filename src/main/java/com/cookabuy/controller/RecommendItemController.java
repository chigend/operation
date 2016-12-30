package com.cookabuy.controller;

import com.cookabuy.entity.service.dto.RecommendItemDTO;
import com.cookabuy.entity.service.dto.RecommendItemEntity;
import com.cookabuy.entity.service.po.Item;
import com.cookabuy.entity.service.po.Recommend;
import com.cookabuy.repository.service.ItemRepository;
import com.cookabuy.repository.service.RecommendRepository;
import com.cookabuy.spring.aop.annotation.MenuItem;
import com.cookabuy.thirdParty.dozer.DozerHelper;
import com.cookabuy.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

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
    @RequestMapping("/recommend_item")
    public Result recommendItem(@RequestBody RecommendItemEntity data) {
        //todo 修改该参数类名
        List<Recommend> recommendItems = dozerHelper.mapList(data.getItems(), Recommend.class);
        int maxPosition = recommendRepository.findMaxPositionByPage(data.getPageName());
        for (Recommend recommendItem : recommendItems){
            Recommend recommend = recommendRepository.findByPageNameAndLocationAndItemId(data.getPageName(), data.getLocation(), recommendItem.getItemId());

            if (recommend != null) {
                continue;   //同一模块下有相同的商品则跳过，同一模块指同一pageName 和同一location
            }
            recommendItem.setLocation(data.getLocation());
            recommendItem.setPageName(data.getPageName());
            recommendItem.setInsertedAt(new Date());
            recommendItem.setUpdatedAt(new Date());
            recommendItem.setPosition(++maxPosition);
            recommendRepository.save(recommendItem);
        }
        return new Result("items", recommendItems);
    }

    @RequestMapping("list_items")
    @MenuItem
    public Result listItems(String pageName, String location, Result result) {
        List<Recommend> recommendItems = recommendRepository.findByPageNameAndLocationOrderByPositionAsc(pageName, location);
        List<RecommendItemDTO> dtos = dozerHelper.mapList(recommendItems,RecommendItemDTO.class);
        dtos.stream().forEach(dto -> {
            Item item = itemRepository.findOne(dto.getItemId());
            //todo 如果商品不存在 ，则表示该商品失效，应设置flag
            if (item != null){
                dto.setTitle(item.getTitle());
                dto.setPrice(item.getPrice());
                dto.setShopName(item.getShopName());
            }
            //todo  设置market
        });
        result.addData("stores", dtos);
        return result;
    }

    @RequestMapping("delete_item")
    public Result deleteItems (@RequestBody List<Integer> ids) {
        recommendRepository.deleteRecommendWithIds(ids);
        return new Result();
    }
}
