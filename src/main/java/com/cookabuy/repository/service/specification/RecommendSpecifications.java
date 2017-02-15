package com.cookabuy.repository.service.specification;

import com.cookabuy.entity.service.po.RecommendItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 2016/12/8
 */
@Slf4j
public class RecommendSpecifications {
    //根据page_name,location,position精确定位
    public static Specification<RecommendItem> findByAbsolutePosition(RecommendItem recommend){
        return new Specification<RecommendItem>() {
            @Override
            public Predicate toPredicate(Root<RecommendItem> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                return cb.and(cb.equal(root.get("pageName"),recommend.getPageName()),
                                cb.equal(root.get("location"),recommend.getLocation()),
                                    cb.equal(root.get("position"),recommend.getPosition()));
            }
        };

    }
    public static Specification<RecommendItem> findByRecommend(RecommendItem recommend){
        return new Specification<RecommendItem>() {
            @Override
            public Predicate toPredicate(Root<RecommendItem> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<>();
                Class clazz = recommend.getClass();
                for(Field field:clazz.getDeclaredFields()){
                    if(!field.isAccessible()){
                        field.setAccessible(true);
                    }
                    String name = field.getName();
                    Object value = null;
                    try {
                        value = field.get(recommend);
                    } catch (IllegalAccessException e) {
                        log.info("access to field {} of {} fail",name,clazz);
                    }
                    if(value != null){
                        predicateList.add(cb.equal(root.get(name),value));
                    }
                }
                return cb.and(predicateList.toArray(new Predicate[0]));
            }
        };
    }
}
