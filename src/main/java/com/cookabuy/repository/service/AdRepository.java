package com.cookabuy.repository.service;

import com.cookabuy.entity.service.po.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Transactional
public interface AdRepository extends JpaRepository<Ad, UUID> {
    @Override
    <S extends Ad> S save(S entity);

    @Override
    Ad findOne(UUID id);

    @Override
    boolean exists(UUID id);

    @Override
    long count();

    @Override
    void delete(UUID id);

    @Override
    void delete(Ad entity);

    @Override
    void delete(Iterable<? extends Ad> entities);

    @Override
    void deleteAll();


    @Query(value = "select * from ad where page_name=?1 and deleted=false order by position asc", nativeQuery = true)
    List<Ad> findByPageNameOrderByPositionAsc(String pageName);

    @Modifying
    @Query(value = "update Ad ad set ad.position = ?2 where ad.adId=?1")
    void updatePositionById(Integer adId, Integer position);

    /**
     * 根据id 对ad表中的is_hidden字段进行取反操作
     *
     * @param id
     */
    @Modifying
    @Query(value = "update Ad ad set ad.hidden = case ad.hidden when true then false else true end where ad.adId = ?1")
    void toggleHiddenByAdId(UUID id);

    @Query(value = "select ad from Ad ad where ad.pageName=?1 and ad.location=?2 and ad.deleted=false")
    List<Ad> findByPageNameAndLocation(String pageName, String location);


    @Modifying
    @Query(value = "update Ad ad set ad.deleted = true,ad.position = 0 where ad.adId in ?1")
    void logicDelete(List<UUID> ids);


    @Modifying
    @Query(value = "update Ad ad set ad.position = ?1 where ad.position = ?2 and ad.pageName = ?3")
    void updateAdPositionByPageName(Integer position, Integer original, String pageName);

    @Query(value = "select coalesce(max(ad.position),0) from Ad ad where ad.pageName = ?1 and ad.deleted=false ")
    Integer findMaxPositionByPageName(String pageName);

    @Query(value = "select case when count(*) > 0 then true else false end from Ad ad where ad.modifyTime > (select coalesce(max(p.publishTime),'1970-01-01 00:00:00') from PublishLog p where p.type =?1)")
    boolean publishActivate(String type);

    @Query(value = "select ad from Ad ad where ad.pageName=?1 and ad.deleted=false")
    List<Ad> findByPageName(String pageName);

    @Modifying
    @Query(value = "delete from Ad ad where ad.pageName=?1 and ad.deleted=true")
    void deleteHasDeletedFlagByPageName(String pageName);

    @Modifying
    @Query(value = "delete from Ad ad where ad.pageName=?1 and ad.location=?2 and ad.deleted=true")
    void deleteHasDeletedFlagByPageNameAndLocation(String pageName, String location);
}
