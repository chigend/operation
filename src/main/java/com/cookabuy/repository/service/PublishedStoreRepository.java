package com.cookabuy.repository.service;

import com.cookabuy.entity.service.po.PublishedStore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PublishedStoreRepository extends JpaRepository<PublishedStore,UUID> {
    @Override
    <S extends PublishedStore> S save(S entity);

    @Override
    PublishedStore findOne(UUID uuid);

    @Override
    boolean exists(UUID uuid);

    @Override
    long count();

    @Override
    void delete(UUID uuid);

    @Override
    void delete(PublishedStore entity);

    @Override
    void delete(Iterable<? extends PublishedStore> entities);

    @Override
    void deleteAll();

    List<PublishedStore> findByPage(String page);
}
