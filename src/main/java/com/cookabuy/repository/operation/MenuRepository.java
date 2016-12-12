package com.cookabuy.repository.operation;

import com.cookabuy.entity.operation.po.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu,Integer> {
    @Override
    <S extends Menu> S save(S entity);

    @Override
    Menu findOne(Integer id);

    @Override
    boolean exists(Integer id);

    @Override
    long count();

    @Override
    void delete(Integer id);

    @Override
    void delete(Menu entity);

    @Override
    void delete(Iterable<? extends Menu> entities);

    @Override
    void deleteAll();

}
