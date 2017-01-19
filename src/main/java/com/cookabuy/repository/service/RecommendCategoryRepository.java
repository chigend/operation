package com.cookabuy.repository.service;

import com.cookabuy.entity.service.po.RecommendCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendCategoryRepository extends JpaRepository<RecommendCategory,Integer> {
    @Override
    <S extends RecommendCategory> S save(S entity);

    @Override
    RecommendCategory findOne(Integer id);

    @Override
    boolean exists(Integer id);

    @Override
    long count();

    @Override
    void delete(Integer id);

    @Override
    void delete(RecommendCategory entity);

    @Override
    void delete(Iterable<? extends RecommendCategory> entities);

    @Override
    void deleteAll();

    List<RecommendCategory> findByPageNameOrderByOrder(String pageName);

}
