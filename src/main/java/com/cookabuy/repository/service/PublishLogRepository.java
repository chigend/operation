package com.cookabuy.repository.service;

import com.cookabuy.entity.service.po.PublishLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublishLogRepository extends JpaRepository<PublishLog,Integer> {
    @Override
    <S extends PublishLog> S save(S entity);

    @Override
    PublishLog findOne(Integer id);

    @Override
    boolean exists(Integer id);

    @Override
    long count();

    @Override
    void delete(Integer id);

    @Override
    void delete(PublishLog entity);

    @Override
    void delete(Iterable<? extends PublishLog> entities);

    @Override
    void deleteAll();

}
