package com.cookabuy.repository.operation;


import com.cookabuy.entity.operation.po.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation,Integer> {
    @Override
    <S extends Operation> S save(S entity);

    @Override
    Operation findOne(Integer id);

    @Override
    boolean exists(Integer id);

    @Override
    long count();

    @Override
    void delete(Integer id);

    @Override
    void delete(Operation entity);

    @Override
    void delete(Iterable<? extends Operation> entities);

    @Override
    void deleteAll();

    @Query(value = "select op_id from user_op where user_id=?1",nativeQuery = true)
    public List<Integer> findOperationIdsByUserId(Integer userId);

}
