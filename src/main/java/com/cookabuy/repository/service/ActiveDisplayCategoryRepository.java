package com.cookabuy.repository.service;

/**
 * @author yejinbiao
 * @create 2017-02-24-上午10:13
 */


import com.cookabuy.entity.service.po.ActiveDisplayCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author yejinbiao
 * @create 2017-02-22-下午2:23
 */

public interface ActiveDisplayCategoryRepository extends JpaRepository<ActiveDisplayCategory, UUID> {

    @Override
    <S extends ActiveDisplayCategory> S save(S entity);

    @Override
    ActiveDisplayCategory findOne(UUID uuid);

    @Override
    boolean exists(UUID uuid);

    @Override
    long count();

    @Override
    void delete(UUID uuid);

    @Override
    void delete(ActiveDisplayCategory entity);

    @Override
    void delete(Iterable<? extends ActiveDisplayCategory> entities);


}

