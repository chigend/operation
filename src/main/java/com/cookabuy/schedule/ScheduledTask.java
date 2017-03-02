package com.cookabuy.schedule;

import com.cookabuy.entity.service.po.RecommendStore;
import com.cookabuy.repository.service.RecommendItemRepository;
import com.cookabuy.repository.service.RecommendStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.cookabuy.constant.PageContant.INDEX;

/**
 * @author yejinbiao
 * @create 2016-12-30-11:23
 */
@Component
public class ScheduledTask {

    @Autowired
    private RecommendStoreRepository recommendStoreRepository;
    @Autowired
    private RecommendItemRepository recommendRepository;
    //todo 正式上服务器应改成合适的cron表达式


//    @Scheduled(cron = "* * 20 * * ?")
//    @Scheduled(fixedRate = 10 * 60 * 1000)
    public void deleteDeletedFlagItem() {
        int initPosition = 1; //初始position为1
        for (RecommendStore store : recommendStoreRepository.findByPageOrderByPositionAsc(INDEX)) {
            store.setPosition(initPosition ++);
            recommendStoreRepository.save(store);
        }
    }

}
