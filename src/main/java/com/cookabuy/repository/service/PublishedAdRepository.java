package com.cookabuy.repository.service;

import com.cookabuy.entity.service.po.PublishedAd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
@Transactional
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
    @Modifying
    @Query(value = "delete  PublishedAd ad where ad.pageName=?1 and ad.location=?2")
    void deleteByPageNameAndLocation(String pageName, String location);

    @Modifying
    @Query(value = "delete PublishedAd ad where ad.pageName=?1")
    void deleteByPageName(String pageName);
}
