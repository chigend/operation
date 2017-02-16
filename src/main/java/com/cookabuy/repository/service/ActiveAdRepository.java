package com.cookabuy.repository.service;

import com.cookabuy.entity.service.po.ActiveAd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ActiveAdRepository extends JpaRepository<ActiveAd,UUID> {
    @Override
    <S extends ActiveAd> S save(S entity);

    @Override
    ActiveAd findOne(UUID uuid);

    @Override
    boolean exists(UUID uuid);

    @Override
    long count();

    @Override
    void delete(UUID uuid);

    @Override
    void delete(ActiveAd entity);

    @Override
    void delete(Iterable<? extends ActiveAd> entities);

    @Override
    void deleteAll();
}
