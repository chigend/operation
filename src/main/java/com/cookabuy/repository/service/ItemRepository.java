package com.cookabuy.repository.service;
import com.cookabuy.entity.service.po.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.lang.annotation.Native;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Long>,JpaSpecificationExecutor<Item> {
    @Override
    <S extends Item> S save(S entity);

    @Override
    Item findOne(Long id);

    @Override
    boolean exists(Long id);

    @Override
    long count();

    @Override
    void delete(Long id);

    @Override
    void delete(Item entity);

    @Override
    void delete(Iterable<? extends Item> entities);

    @Override
    void deleteAll();

    @Override
    Page findAll(Specification <Item>spec, Pageable pageable);

    @Override
    List<Item> findAll(Specification<Item> spec);

    @Query(value="select * from tb_item  limit 1000",nativeQuery = true)
    public List<Item> findTop1000();

    @Query(value = "select * from tb_item limit ?1 offset ?2",nativeQuery = true)
    public List<Item> findTopAndOffset(Integer limit,Integer offset);

    public List<Item> findByTitleLike(String title);
}
