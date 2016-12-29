package com.cookabuy.controller;

import com.cookabuy.entity.service.dto.RecommendItemDTO;
import com.cookabuy.entity.service.dto.RecommendItemEntity;
import com.cookabuy.entity.service.dto.RecommendStoreDTO;
import com.cookabuy.entity.service.po.Recommend;
import com.cookabuy.entity.service.po.RecommendStore;
import com.cookabuy.repository.service.RecommendRepository;
import com.cookabuy.thirdParty.dozer.DozerHelper;
import com.cookabuy.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.cookabuy.constant.PageContant.INDEX;

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
    @RequestMapping("/recommend_item")
    public Result recommendItem(@RequestBody RecommendItemEntity data) {
        //todo 修改该参数类名
        List<Recommend> recommendItems = dozerHelper.mapList(data.getItems(), Recommend.class);
        int maxPosition = recommendRepository.findMaxPositionByPage(data.getPageName());
        for (Recommend recommendItem : recommendItems){
            recommendItem.setLocation(data.getLocation());
            recommendItem.setInsertedAt(new Date());
            recommendItem.setUpdatedAt(new Date());
            recommendItem.setPageName(INDEX);
            recommendItem.setPosition(++maxPosition);
            recommendRepository.save(recommendItem);
        }
        return new Result();
    }
}
