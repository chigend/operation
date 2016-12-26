package com.cookabuy.repository.operation;
import com.cookabuy.entity.operation.po.UserOp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserOpRepository extends JpaRepository<UserOp,Integer> {
    @Override
    <S extends UserOp> S save(S entity);

    @Override
    UserOp findOne(Integer id);

    @Override
    boolean exists(Integer id);

    @Override
    long count();

    @Override
    void delete(Integer id);

    @Override
    void delete(UserOp entity);

    @Override
    void delete(Iterable<? extends UserOp> entities);

    @Override
    void deleteAll();

    @Modifying
    @Query(value = "delete from user_op where user_id=?1",nativeQuery = true)
    void deleteByUserId(Integer userId);

}
