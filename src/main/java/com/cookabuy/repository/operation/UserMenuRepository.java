package com.cookabuy.repository.operation;

import com.cookabuy.entity.operation.po.UserMenu;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yejinbiao
 * @create 2016-12-13-下午2:36
 */

public interface UserMenuRepository extends JpaRepository<UserMenu,Integer>{
    @Override
    <S extends UserMenu> S save(S entity);

    @Override
    UserMenu findOne(Integer integer);

    @Override
    boolean exists(Integer integer);

    @Override
    long count();

    @Override
    void delete(Integer integer);

    @Override
    void delete(UserMenu entity);

    @Override
    void delete(Iterable<? extends UserMenu> entities);

    @Override
    void deleteAll();
}
