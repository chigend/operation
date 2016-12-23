package com.cookabuy.service;

//import com.cookabuy.repository.service.RecommendRepository;
import com.cookabuy.entity.service.dto.ReplaceRecommendForm;
import com.cookabuy.entity.service.po.Recommend;
import com.cookabuy.repository.service.RecommendRepository;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.cookabuy.repository.service.specification.RecommendSpecifications.*;

/**
 * 2016/12/6
 */
@Service
@Slf4j
public class RecommendService {
    @Autowired
    private RecommendRepository recommendRepository;
    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    @Transactional
    public void replaceRecommend(ReplaceRecommendForm form){

        log.info("the replace form is {}",form);
        Recommend recommend = dozerBeanMapper.map(form,Recommend.class);
        Recommend old = recommendRepository.findOne(findByAbsolutePosition(recommend));
        if(old == null){
            recommendRepository.save(recommend);
        }else {
            recommendRepository.replaceRecommend(recommend.getItemId(),recommend.getPageName(),recommend.getLocation(),recommend.getPosition());
        }

    }

}
