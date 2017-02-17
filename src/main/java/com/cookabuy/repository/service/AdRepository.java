package com.cookabuy.repository.service;
import com.cookabuy.entity.service.po.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Transactional
public interface AdRepository extends JpaRepository<Ad,UUID> {
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


    @Query(value = "select * from ad where page_name=?1 and deleted=false order by position asc",nativeQuery = true)
    List<Ad> findByPageNameOrderByPositionAsc(String pageName);

    @Modifying
    @Query(value = "update ad set position = ?2 where ad_id=?1",nativeQuery = true)
    void updatePositionById(Integer adId,Integer position);

    /**
     * 根据id 对ad表中的is_hidden字段进行取反操作
     * @param id
     */
    @Modifying
    @Query(value = "update ad set is_hidden = not is_hidden where ad_id=?1",nativeQuery = true)

    void toggleHiddenByAdId(UUID id);

    List<Ad> findByPageNameAndLocation(String pageName, String location);

    @Query(value = "select count(modify_time)>0 as activate from ad where modify_time > (select max(publish_time) from publish_log)", nativeQuery = true )
    boolean publishActicate();

    @Modifying
    @Query(value = "update ad set deleted = true where ad_id in ?1",nativeQuery = true)
    void logicDelete(List<UUID> ids);


}
