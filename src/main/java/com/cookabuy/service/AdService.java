package com.cookabuy.service;

import com.cookabuy.constant.AdDirection;
import com.cookabuy.constant.PublishType;
import com.cookabuy.entity.service.po.Ad;
import com.cookabuy.entity.service.po.PublishLog;
import com.cookabuy.repository.service.ActiveAdRepository;
import com.cookabuy.repository.service.AdRepository;
import com.cookabuy.repository.service.PublishLogRepository;
import com.cookabuy.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static com.cookabuy.constant.ErrorConstant.CAN_NOT_MOVE;
import static com.cookabuy.constant.PageContant.INDEX;

/**
 * @author yejinbiao
 * @create 2017-02-13-下午3:09
 */
@Service
public class AdService {
    @Autowired
    private AdRepository adRepository;
    @Autowired
    private ActiveAdRepository activeAdRepository;
    @Autowired
    private PublishLogRepository publishLogRepository;
    @Transactional(value = "serviceTransactionManager", rollbackFor = Exception.class)
    public void publishAds() {
        //清空所有已发布的广告
        activeAdRepository.deleteAll();
        //重新添加所有启用的广告
        adRepository.findByPageNameOrderByPositionAsc(INDEX).stream().filter(ad -> !ad.isHidden())
                .forEach(ad -> {
//            ActiveAd aa = new ActiveAd(ad.getAdId());
//            activeAdRepository.save(aa);
        });
        publishLogRepository.save(new PublishLog(PublishType.AD, new Date()));

    }
    @Transactional(value = "serviceTransactionManager", rollbackFor = Exception.class)
    public Result moveAd(Ad ad, AdDirection direction) {
        Integer position = ad.getPosition();
        switch (direction) {
            case UP:
                if (position == 1) {
                    return new Result(CAN_NOT_MOVE);
                }

                adRepository.updateAdPosition(position,position - 1);
                ad.setPosition(position - 1);
                adRepository.save(ad);
                break;
            case DOWN:
                Integer maxPosition = adRepository.findMaxPositionByPageName(INDEX);
                if (position == maxPosition) {
                    return new Result(CAN_NOT_MOVE);
                }

                adRepository.updateAdPosition(position,position + 1);
                ad.setPosition(position + 1);
                adRepository.save(ad);
                break;
        }
        return new Result();
    }

}
