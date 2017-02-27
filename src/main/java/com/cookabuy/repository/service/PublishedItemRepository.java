package com.cookabuy.repository.service;

import com.cookabuy.entity.service.po.PublishedItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @author yejinbiao
 * @create 2017-02-22-上午9:25
 */


@Transactional
public interface PublishedItemRepository extends JpaRepository<PublishedItem,UUID> {
    @Override
    <S extends PublishedItem> S save(S entity);

    @Override
    PublishedItem findOne(UUID uuid);

    @Override
    boolean exists(UUID uuid);

    @Override
    long count();

    @Override
    void delete(UUID uuid);

    @Override
    void delete(PublishedItem entity);

    @Override
    void delete(Iterable<? extends PublishedItem> entities);

    @Override
    void deleteAll();

    @Modifying
    @Query("delete from PublishedItem item where item.pageName=?1 and item.location=?2")
    void deleteByPageNameAndLocation(String pageName, String location);
}
