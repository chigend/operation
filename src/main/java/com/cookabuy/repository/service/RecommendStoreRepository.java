package com.cookabuy.repository.service;
import com.cookabuy.entity.service.po.RecommendStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Transactional
public interface RecommendStoreRepository extends JpaRepository<RecommendStore,UUID> {
    @Override
    <S extends RecommendStore> S save(S entity);

    @Override
    RecommendStore findOne(UUID id);

    @Override
    boolean exists(UUID id);

    @Override
    long count();

    @Override
    void delete(UUID id);

    @Override
    void delete(RecommendStore entity);

    @Override
    void delete(Iterable<? extends RecommendStore> entities);

    @Override
    void deleteAll();

    RecommendStore findByStoreId(UUID storeId);

    @Modifying
    @Query(value = "update recommend_stores set pic_url = ?1 where store_id=?2",nativeQuery = true)
    void updatePicUrlByStoreId(String picUrl,Long storeId);


    List<RecommendStore> findByPageOrderByPositionAsc(String pageName);

    @Query(value = "select COALESCE(max(COALESCE(position,0)),0) from recommend_stores where page = ?1",nativeQuery = true)
    int findMaxPositionByPage(String page);

    @Query(value = "select case when count(*) > 0 then true else false end from RecommendStore store where store.storeId = ?1")
    boolean existRecommendStore(UUID storeId);

    @Modifying
    @Query(value = "update recommend_stores set is_effective = not is_effective where id = ?1",nativeQuery = true)
    void toggleEffectiveById(Integer id);

    @Modifying
    @Query(value = "update RecommendStore store set store.storeId = ?1 where store.position = ?2 and store.page = ?3")
    void updateRecommendStore(UUID storeId, Integer position, String pageName );
}
