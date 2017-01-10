package com.cookabuy.repository.operation;

import com.cookabuy.entity.operation.po.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
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

    /**
     * 根据userId查询当前用户所能操作的menu
     * @param userId
     * @return
     */
    @Query(value = "select m.* from menu m join user_menu um on um.user_id=?1 and  um.menu_id = m.id",nativeQuery = true)
    List<Menu> findMenuByUserId(Integer userId);

    @Override
    List<Menu> findAll();

    @Query(value = "select * from menu where for_admin = false", nativeQuery = true)
    List<Menu> findAllMenuForOrdinaryUser();

    @Modifying
    @Query(value = "delete from user_menu where user_id=?1",nativeQuery = true)
    void deleteByUserId(Integer userId);


}
