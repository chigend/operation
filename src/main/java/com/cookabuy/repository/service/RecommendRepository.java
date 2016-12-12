package com.cookabuy.repository.service;

import com.cookabuy.entity.service.po.Recommend;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface RecommendRepository extends JpaRepository<Recommend, Long>,JpaSpecificationExecutor<Recommend>{
    @Override
    <S extends Recommend> S save(S entity);

    @Override
    Recommend findOne(Specification<Recommend> spec);

    @Override
    List<Recommend> findAll(Specification<Recommend> spec);

    @Override
    Page<Recommend> findAll(Specification<Recommend> spec, Pageable pageable);

    @Override
    List<Recommend> findAll(Specification<Recommend> spec, Sort sort);

    @Override
    long count(Specification<Recommend> spec);

    @Modifying
    @Query("update Recommend recommend set recommend.itemId =?1 where recommend.pageName=?2 " +
            "and recommend.location=?3 and recommend.position=?4")
    public void replaceRecommend(Long numiid, String pagename, String location, Integer position);

}
