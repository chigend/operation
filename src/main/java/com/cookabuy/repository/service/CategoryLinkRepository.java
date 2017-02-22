package com.cookabuy.repository.service;

import com.cookabuy.entity.service.po.CategoryLink;
import com.cookabuy.entity.service.po.CategoryLinkPK;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 2017/2/22
 */
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
}
