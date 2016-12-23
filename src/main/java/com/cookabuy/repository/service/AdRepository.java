package com.cookabuy.repository.service;
import com.cookabuy.entity.service.po.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdRepository extends JpaRepository<Ad,Integer> {
    @Override
    <S extends Ad> S save(S entity);

    @Override
    Ad findOne(Integer id);

    @Override
    boolean exists(Integer id);

    @Override
    long count();

    @Override
    void delete(Integer id);

    @Override
    void delete(Ad entity);

    @Override
    void delete(Iterable<? extends Ad> entities);

    @Override
    void deleteAll();


    List<Ad> findByPageNameOrderByPositionAsc(String pageName);

}
