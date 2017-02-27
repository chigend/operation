package com.cookabuy.repository.service;

import com.cookabuy.entity.service.po.DisplayCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @author yejinbiao
 * @create 2017-02-22-下午2:23
 */
@Transactional
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
    @Query(value = "select  d from DisplayCategory d where d.pid is null ")
    List<DisplayCategory> findFirstLevelCategory();

    @Query(value = "select d from DisplayCategory d where d.pid = ?1")
    List<DisplayCategory> findByPid(UUID pid);

    @Query(value = "select count(*) from DisplayCategory d where d.pid = ?1")
    int findChildCount(UUID pid);

    @Modifying
    @Query("update DisplayCategory d set d.name=?1,d.display=?2,d.weight=?3 where d.id=?4")
    void updateDisplatCategory(String name, boolean display, Integer weight, UUID id);
}
