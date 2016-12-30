package com.cookabuy.repository.operation;

import com.cookabuy.entity.operation.po.OperationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(transactionManager = "operationTransactionManager")
public interface OperationUserRepository extends JpaRepository<OperationUser,Integer> {
    @Override
    <S extends OperationUser> S save(S entity);

    @Override
    OperationUser findOne(Integer id);

    @Override
    boolean exists(Integer id);

    @Override
    <S extends OperationUser> S saveAndFlush(S entity);

    @Override
    long count();

    @Override
    void delete(Integer id);

    @Override
    void delete(OperationUser entity);

    @Override
    void delete(Iterable<? extends OperationUser> entities);

    @Override
    void deleteAll();

    OperationUser findByUsername(String username);


    @Query(value = "select * from operation_user where is_administrator is false",nativeQuery = true)
    List<OperationUser> findAllNotAdminOperationUser();

}
