package com.cookabuy.repository.service;

import com.cookabuy.entity.service.po.DisplayCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

/**
 * @author yejinbiao
 * @create 2017-02-22-下午2:23
 */

public interface DisplayCategoryRepository extends JpaRepository<DisplayCategory,UUID>  {

    @Override
    <S extends DisplayCategory> S save(S entity);

    @Override
    DisplayCategory findOne(UUID uuid);

    @Override
    boolean exists(UUID uuid);

    @Override
    long count();

    @Override
    void delete(UUID uuid);

    @Override
    void delete(DisplayCategory entity);

    @Override
    void delete(Iterable<? extends DisplayCategory> entities);

    @Override
    void deleteAll();
    @Query(value = "select  d from DisplayCategory d where d.id = d.pid")
    List<DisplayCategory> findFirstLevelCategory();

    List<DisplayCategory> findByPid(UUID pid);
}
