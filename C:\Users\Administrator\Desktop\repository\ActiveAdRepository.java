package com.cookabuy.repository;
import com.cookabuy.entity.ActiveAd;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActiveAdRepository extends JpaRepository<ActiveAd,Integer> {
    @Override
    <S extends ActiveAd> S save(S entity);

    @Override
    ActiveAd findOne(Integer id);

    @Override
    boolean exists(Integer id);

    @Override
    long count();

    @Override
    void delete(Integer id);

    @Override
    void delete(ActiveAd entity);

    @Override
    void delete(Iterable<? extends ActiveAd> entities);

    @Override
    void deleteAll();

}
