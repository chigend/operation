package com.cookabuy.repository.operation;

import com.cookabuy.entity.operation.po.UserPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yejinbiao
 * @create 2016-12-13-下午3:45
 */
@Transactional
public interface UserPermissionRepository extends JpaRepository<UserPermission,Integer> {
    @Override
    <S extends UserPermission> S save(S entity);

    @Override
    UserPermission findOne(Integer integer);

    @Override
    boolean exists(Integer integer);

    @Override
    long count();

    @Override
    void delete(Integer integer);

    @Override
    void delete(UserPermission entity);

    @Override
    void delete(Iterable<? extends UserPermission> entities);

    @Override
    void deleteAll();

    @Modifying
    @Query(value = "delete from user_permission where user_id=?1",nativeQuery = true)
    void deleteByUserId(Integer userId);
}
