package com.cookabuy.util;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 2016/12/13
 */
@Component
public class DozerHelper {
    @Autowired
    private DozerBeanMapper mapper;

    /**
     *
     * @param sourceList 源list
     * @param clazz 目标list包含的对象的Class对象
     * @param <S>  源list中对象的类型
     * @param <D>  目标list中的对象类型
     * @return  目标list
     */
    public  <S,D>List<D> mapList(List<S> sourceList, final Class<D> clazz){
        List<D> destination = new ArrayList<D>();
        for(S source:sourceList){
            if(source != null){
                D d = mapper.map(source,clazz);
                destination.add(d);
            }
        }
        return destination;
    }
}
