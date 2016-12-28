package com.cookabuy.repository.service;
import com.cookabuy.entity.service.po.RecommendStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RecommendStoreRepository extends JpaRepository<RecommendStore,Integer> {
    @Override
    <S extends RecommendStore> S save(S entity);

    @Override
    RecommendStore findOne(Integer id);

    @Override
    boolean exists(Integer id);

    @Override
    long count();

    @Override
    void delete(Integer id);

    @Override
    void delete(RecommendStore entity);

    @Override
    void delete(Iterable<? extends RecommendStore> entities);

    @Override
    void deleteAll();

    RecommendStore findByStoreId(Long storeId);

    @Modifying
    @Query(value = "update recommend_store set pic_url = ?1 where store_id=?2",nativeQuery = true)
    void updatePicUrlByStoreId(String picUrl,Long storeId);

}
