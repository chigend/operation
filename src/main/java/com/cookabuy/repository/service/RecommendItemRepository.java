package com.cookabuy.repository.service;

import com.cookabuy.entity.service.po.ItemPK;
import com.cookabuy.entity.service.po.RecommendItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Transactional
public interface RecommendItemRepository extends JpaRepository<RecommendItem, UUID>,JpaSpecificationExecutor<RecommendItem>{
    @Override
    <S extends RecommendItem> S save(S entity);

    @Override
    RecommendItem findOne(Specification<RecommendItem> spec);

    @Override
    List<RecommendItem> findAll(Specification<RecommendItem> spec);

    @Override
    Page<RecommendItem> findAll(Specification<RecommendItem> spec, Pageable pageable);

    @Override
    List<RecommendItem> findAll(Specification<RecommendItem> spec, Sort sort);

    @Override
    long count(Specification<RecommendItem> spec);

    @Modifying
    @Query("update RecommendItem recommend set recommend.itemId =?1 where recommend.pageName=?2 " +
            "and recommend.location=?3 and recommend.position=?4")
    public void replaceRecommendItem(Long numiid, String pagename, String location, Integer position);

    @Query(value = "select COALESCE(max(COALESCE(position,0)),0) from recommends where page_name = ?1 and location = ?2",nativeQuery = true)
    int findMaxPositionByPageNameAndLocation(String pageName, String location);

    @Query(value = "select case when count (*)>0 then true else false end from recommends where item_id = ?1",nativeQuery = true)
    boolean exists(Long itemId);

    List<RecommendItem> findByPageNameAndLocationOrderByPositionAsc(String pageName, String location);

    @Modifying
    @Query("delete from RecommendItem r where r.id in ?1")
    void deleteRecommendItemWithIds(List<Integer> ids);

    RecommendItem findByPageNameAndLocationAndItemId(String pageName, String location, Long itemId);

}
