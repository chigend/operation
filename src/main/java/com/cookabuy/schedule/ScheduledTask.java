package com.cookabuy.schedule;

import com.cookabuy.entity.service.po.RecommendItem;
import com.cookabuy.entity.service.po.RecommendStore;
import com.cookabuy.repository.service.RecommendItemRepository;
import com.cookabuy.repository.service.RecommendStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.cookabuy.constant.LocationConstant.LOCATION_ARRAY;
import static com.cookabuy.constant.PageContant.INDEX;
import static com.cookabuy.constant.PageContant.PAGE_ARRAY;

/**
 * @author yejinbiao
 * @create 2016-12-30-11:23
 */
@Component
public class ScheduledTask {
    /**
     *   刷新推荐店铺的position  比如推荐店铺的position为1,3,5,200，,345，  刷新后position改为1,2,3,4,5
     *   防止position一直增长
     */
    @Autowired
    private RecommendStoreRepository recommendStoreRepository;
    @Autowired
    private RecommendItemRepository recommendRepository;
    //todo 正式上服务器应改成合适的cron表达式
//    @Scheduled(cron = "* * 20 * * ?")
//    @Scheduled(fixedRate = 10 * 60 * 1000)
    public void refreshRecommendStorePosition() {
        int initPosition = 1; //初始position为1
        for (RecommendStore store : recommendStoreRepository.findByPageOrderByPositionAsc(INDEX)) {
            store.setPosition(initPosition ++);
            recommendStoreRepository.save(store);
        }
    }

    /**
     *  /**
     *   刷新推荐商品的position  同上
     */

//    @Scheduled(cron = "* * 20 * * ?")
    public void refreshRecommendItemPosition() {

        for (String page : PAGE_ARRAY) {
            for (String location : LOCATION_ARRAY) {
                int initPosition = 1; //初始position为1
                for (RecommendItem item : recommendRepository.findByPageNameAndLocationOrderByPositionAsc(page, location)) {
                    item.setPosition(initPosition ++);
                    recommendRepository.save(item);
                }
            }
        }
    }
}
