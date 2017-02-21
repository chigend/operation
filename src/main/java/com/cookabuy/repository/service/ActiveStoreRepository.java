package com.cookabuy.repository.service;

import com.cookabuy.entity.service.po.ActiveStore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ActiveStoreRepository extends JpaRepository<ActiveStore,UUID> {
    @Override
    <S extends ActiveStore> S save(S entity);

    @Override
    ActiveStore findOne(UUID uuid);

    @Override
    boolean exists(UUID uuid);

    @Override
    long count();

    @Override
    void delete(UUID uuid);

    @Override
    void delete(ActiveStore entity);

    @Override
    void delete(Iterable<? extends ActiveStore> entities);

    @Override
    void deleteAll();

    List<ActiveStore> findByPage(String page);
}
