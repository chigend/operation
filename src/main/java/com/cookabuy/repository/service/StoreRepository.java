package com.cookabuy.repository.service;
import com.cookabuy.entity.service.po.Item;
import com.cookabuy.entity.service.po.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store,Integer> {
    @Override
    <S extends Store> S save(S entity);

    @Override
    Store findOne(Integer id);

    @Override
    boolean exists(Integer id);

    @Override
    long count();

    @Override
    void delete(Integer id);

    @Override
    void delete(Store entity);

    @Override
    void delete(Iterable<? extends Store> entities);

    @Override
    void deleteAll();


    @Query(value = "select * from stores limit ?1 offset ?2",nativeQuery = true)
    public List<Store> findTopAndOffset(Integer limit, Integer offset);


}
