package com.cookabuy.repository.service;

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
    public void replaceRecommendItem(UUID numiid, String pagename, String location, Integer position);

    @Query(value = "select COALESCE(max(COALESCE(r.position,0)),0) from RecommendItem  r where r.pageName= ?1 and r.location = ?2")
    int findMaxPositionByPageNameAndLocation(String pageName, String location);

    @Query(value = "select case when count (*)>0 then true else false end from RecommendItem r where r.itemId = ?1")
    boolean exists(UUID itemId);

    @Query(value = "select r from RecommendItem r where r.pageName=?1 and r.location=?2")
    List<RecommendItem> findByPageNameAndLocationOrderByPositionAsc(String pageName, String location);



    @Modifying
    @Query("update RecommendItem  r set  r.deleted=true where r.id in ?1")
    void deleteRecommendItemWithIds(List<UUID> ids);

    @Query("select r from RecommendItem r where r.pageName=?1 and r.location=?2 and r.itemId=?3 " +
            " and r.deleted=false ")
    RecommendItem findByPageNameAndLocationAndItemId(String pageName, String location, UUID itemId);

    @Query(value = "select r from RecommendItem r where r.pageName=?1 and r.location=?2 and r.deleted=false order by " +
            " r.weight desc")
    List<RecommendItem> findByPageNameAndLocationOrderByWeightDesc(String pageName, String location);

    @Query("select case  when count(*) > 0 then true else false end from RecommendItem r where r.pageName=?1 and r.location = ?2 and  r.modifyTime > (select coalesce(max(p.publishTime),'1970-01-01 00:00:00') from PublishLog p where p.type =?3)")
    boolean publishActivate(String pageName,String location,String type);

    @Query(value = "select r from RecommendItem r where r.itemId=?1 and r.deleted = false ")
     RecommendItem findByItemId(UUID itemId);

    /**
     * 根据模块（模块指同一个pageName和location）删除标记为deleted的项
     */
    @Modifying
    @Query(value = "delete from RecommendItem i where i.pageName=?1 and i.location=?2 and i.deleted=true")
    void deleteHasDeletedFlagByPageNameAndLocation(String pageName, String location);



}
