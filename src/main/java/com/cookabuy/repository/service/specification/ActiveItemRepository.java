package com.cookabuy.repository.service.specification;

import com.cookabuy.entity.service.po.ActiveItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author yejinbiao
 * @create 2017-02-22-上午9:25
 */



public interface ActiveItemRepository extends JpaRepository<ActiveItem,UUID> {
    @Override
    <S extends ActiveItem> S save(S entity);

    @Override
    ActiveItem findOne(UUID uuid);

    @Override
    boolean exists(UUID uuid);

    @Override
    long count();

    @Override
    void delete(UUID uuid);

    @Override
    void delete(ActiveItem entity);

    @Override
    void delete(Iterable<? extends ActiveItem> entities);

    @Override
    void deleteAll();
}
