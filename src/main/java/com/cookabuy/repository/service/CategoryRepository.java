package com.cookabuy.repository.service;

import com.cookabuy.entity.service.po.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * @author yejinbiao
 * @create 2017-02-23-上午11:03
 */

public interface CategoryRepository extends JpaRepository<Category,Integer>{
    @Override
    <S extends Category> S save(S entity);

    @Override
    Category findOne(Integer id);

    @Override
    boolean exists(Integer id);

    @Override
    long count();

    @Override
    void delete(Integer id);

    @Override
    void delete(Category entity);

    @Override
    void delete(Iterable<? extends Category> entities);

    @Override
    void deleteAll();

    List<Category> findByPid(Integer pid);
}
