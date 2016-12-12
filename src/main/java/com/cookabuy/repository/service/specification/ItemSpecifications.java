package com.cookabuy.repository.service.specification;

import com.cookabuy.entity.service.po.Item;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * 2016/12/6
 */
public class ItemSpecifications {
    //商品根据title的模糊查询
    public static Specification<Item> findByTitle(String title) {
        return new Specification<Item>() {
            @Override
            public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(root.get("title"), createLikeString(title));
            }
        };
    }

    //根据商品所属的市场名来查询
    public static Specification<Item> findByMarket(String marketname){
        return new Specification<Item>() {
            @Override
            public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //如果市场名不为空，则以市场名为过滤条件
                if(StringUtils.hasLength(marketname)){
                    return marketname == null ? cb.conjunction():cb.equal(root.get("store").get("market"),marketname);
                }
                //如果marketname为空,则该过滤条件忽略.
                return cb.conjunction();
            }
        };
    }

    //根据商品所对应的categoryId来过滤商品
    public static Specification<Item> findByCategoryId(Long categoryId){
        return new Specification<Item>() {

            @Override
            public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                if(categoryId != null){
                    return cb.equal(root.get("cid"),categoryId);
                }
                return cb.conjunction();
            }
        };
    }
    private static String createLikeString(String source) {
        StringBuilder sb = new StringBuilder();
        sb.append("%").append(source).append("%");
        return sb.toString();
    }
}
