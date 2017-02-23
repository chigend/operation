package com.cookabuy.repository.service;

import com.cookabuy.entity.service.po.PublishLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface PublishLogRepository extends JpaRepository<PublishLog,UUID> {
    @Override
    <S extends PublishLog> S save(S entity);

    @Override
    PublishLog findOne(UUID id);

    @Override
    boolean exists(UUID id);

    @Override
    long count();

    @Override
    void delete(UUID id);

    @Override
    void delete(PublishLog entity);

    @Override
    void delete(Iterable<? extends PublishLog> entities);

    @Override
    void deleteAll();

    @Query(value = "select case  when count(*) > 0 then true else false end from RecommendStore r where r.modifyTime > (select max(p.publishTime) from PublishLog p where p.type =?1)" )
    boolean publishActivate(String type);

}
