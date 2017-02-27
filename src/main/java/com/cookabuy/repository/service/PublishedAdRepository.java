package com.cookabuy.repository.service;

import com.cookabuy.entity.service.po.PublishedAd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PublishedAdRepository extends JpaRepository<PublishedAd,UUID> {
    @Override
    <S extends PublishedAd> S save(S entity);

    @Override
    PublishedAd findOne(UUID uuid);

    @Override
    boolean exists(UUID uuid);

    @Override
    long count();

    @Override
    void delete(UUID uuid);

    @Override
    void delete(PublishedAd entity);

    @Override
    void delete(Iterable<? extends PublishedAd> entities);

    @Override
    void deleteAll();
}
