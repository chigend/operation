package com.cookabuy.repository.service;

/**
 * @author yejinbiao
 * @create 2017-02-24-上午10:14
 */


import com.cookabuy.entity.service.po.PublishedCategoryLink;
import com.cookabuy.entity.service.po.CategoryLinkPK;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 2017/2/22
 */
public interface PublishedCategoryLinkRepository extends JpaRepository<PublishedCategoryLink,CategoryLinkPK> {
    @Override
    <S extends PublishedCategoryLink> S save(S entity);

    @Override
    PublishedCategoryLink findOne(CategoryLinkPK id);

    @Override
    boolean exists(CategoryLinkPK id);

    @Override
    long count();

    @Override
    void delete(CategoryLinkPK id);

    @Override
    void delete(PublishedCategoryLink entity);

    @Override
    void delete(Iterable<? extends PublishedCategoryLink> entities);

    @Override
    void deleteAll();

}
