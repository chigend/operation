package com.cookabuy.repository.service;
import com.cookabuy.entity.service.po.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public interface AdRepository extends JpaRepository<Ad,Integer> {
    @Override
    <S extends Ad> S save(S entity);

    @Override
    Ad findOne(Integer id);

    @Override
    boolean exists(Integer id);

    @Override
    long count();

    @Override
    void delete(Integer id);

    @Override
    void delete(Ad entity);

    @Override
    void delete(Iterable<? extends Ad> entities);

    @Override
    void deleteAll();


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

    void toggleHiddenByAdId(Integer id);

    List<Ad> findByPageNameAndLocation(String pageName, String location);

}
