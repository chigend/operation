package com.cookabuy.repository.operation;

import com.cookabuy.entity.operation.po.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission,Integer> {
    @Override
    <S extends Permission> S save(S entity);

    @Override
    Permission findOne(Integer id);

    @Override
    boolean exists(Integer id);

    @Override
    long count();

    @Override
    void delete(Integer id);

    @Override
    void delete(Permission entity);

    @Override
    void delete(Iterable<? extends Permission> entities);

    @Override
    void deleteAll();

    @Query(value = "select p.permission from permission p INNER JOIN user_permission up " +
            "on up.permission_id = p.permission_id where up.user_id = ?1", nativeQuery = true)
    List<String> findPermissionNameByUserId(Integer userId);

}
