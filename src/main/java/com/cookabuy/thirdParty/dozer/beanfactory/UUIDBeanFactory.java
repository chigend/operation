package com.cookabuy.thirdParty.dozer.beanfactory;

import org.dozer.BeanFactory;

import java.util.UUID;

/**
 * @author yejinbiao
 * @create 2017-02-17-上午10:16
 */

public class UUIDBeanFactory implements BeanFactory {
    @Override
    public Object createBean(Object source, Class<?> sourceClass, String targetBeanId) {
        if (source == null) {
            return null;
        }
        UUID sourceBean = (UUID) source;
        UUID des = new UUID(sourceBean.getMostSignificantBits(),sourceBean.getLeastSignificantBits());
        return des;
    }
}
