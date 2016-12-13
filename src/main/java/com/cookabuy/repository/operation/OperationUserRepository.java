package com.cookabuy.repository.operation;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cookabuy.entity.operation.po.*;
import org.springframework.transaction.annotation.Transactional;

@Transactional(transactionManager = "operationTransactionManager")
public interface OperationUserRepository extends JpaRepository<OperationUser,Integer> {
    @Override
    <S extends OperationUser> S save(S entity);

    @Override
    OperationUser findOne(Integer id);

    @Override
    boolean exists(Integer id);

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

}
