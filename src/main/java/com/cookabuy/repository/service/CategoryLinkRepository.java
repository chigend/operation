package com.cookabuy.repository.service;

import com.cookabuy.entity.service.po.CategoryLink;
import com.cookabuy.entity.service.po.CategoryLinkPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * 2017/2/22
 */
@Transactional
public interface CategoryLinkRepository  extends  JpaRepository<CategoryLink,CategoryLinkPK>{
    @Override
    <S extends CategoryLink> S save(S entity);

    @Override
    CategoryLink findOne(CategoryLinkPK id);

    @Override
    boolean exists(CategoryLinkPK id);

    @Override
    long count();

    @Override
    void delete(CategoryLinkPK id);

    @Override
    void delete(CategoryLink entity);

    @Override
    void delete(Iterable<? extends CategoryLink> entities);

    @Override
    void deleteAll();

    List<CategoryLink> findByDisplayId(UUID displayId);

    @Query("select count (*) from CategoryLink c where c.displayId = ?1")
    int findChildsCount(UUID displayId);

    @Modifying
    @Query("delete from CategoryLink cl where cl.cid = ?1")
    void deleteByCid(Integer cid);

}
