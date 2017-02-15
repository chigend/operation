package com.cookabuy.service;

//import com.cookabuy.repository.service.RecommendRepository;

import com.cookabuy.repository.service.RecommendItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 2016/12/6
 */
@Service
@Slf4j
public class RecommendService {
    @Autowired
    private RecommendItemRepository recommendRepository;
    @Autowired
    private DozerBeanMapper dozerBeanMapper;


}
