package com.cookabuy.repository.service;

/**
 * @author yejinbiao
 * @create 2017-02-24-上午10:14
 */


import com.cookabuy.entity.service.po.ActiveCategoryLink;
import com.cookabuy.entity.service.po.CategoryLinkPK;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 2017/2/22
 */
public interface ActiveCategoryLinkRepository  extends JpaRepository<ActiveCategoryLink,CategoryLinkPK> {
    @Override
    <S extends ActiveCategoryLink> S save(S entity);

    @Override
    ActiveCategoryLink findOne(CategoryLinkPK id);

    @Override
    boolean exists(CategoryLinkPK id);

    @Override
    long count();

    @Override
    void delete(CategoryLinkPK id);

    @Override
    void delete(ActiveCategoryLink entity);

    @Override
    void delete(Iterable<? extends ActiveCategoryLink> entities);

    @Override
    void deleteAll();

}
