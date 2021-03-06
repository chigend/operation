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

    @Query(value = "select o.name from operation o JOIN user_op u on u.op_id = o.id where u.user_id = ?1 and o.menu_id =?2", nativeQuery = true)
    public List<String> findOperationAvaiableByMenuIdAndUserId(Integer userId, Integer menuId);

}
