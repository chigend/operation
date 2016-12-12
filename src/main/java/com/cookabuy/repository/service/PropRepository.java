package com.cookabuy.repository.service;
import com.cookabuy.entity.service.po.Prop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropRepository extends JpaRepository<Prop,Long> {
    @Override
    <S extends Prop> S save(S entity);

    @Override
    Prop findOne(Long id);

    @Override
    boolean exists(Long id);

    @Override
    long count();

    @Override
    void delete(Long id);

    @Override
    void delete(Prop entity);

    @Override
    void delete(Iterable<? extends Prop> entities);

    @Override
    void deleteAll();

}
