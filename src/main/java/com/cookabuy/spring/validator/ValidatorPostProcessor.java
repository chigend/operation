package com.cookabuy.spring.validator;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;

/**
 * 处理实现了Validator接口的类，并将其加入CompoundValidator中
 *
 * @author yejinbiao
 * @create 2016-12-20-15:50
 */
@Component
public class ValidatorPostProcessor  implements BeanPostProcessor,ApplicationContextAware{
    private ApplicationContext applicationContext;
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof Validator && !bean.getClass().isAssignableFrom(CompoundValidator.class)){
            CompoundValidator validator = applicationContext.getBean(CompoundValidator.class);
            validator.addValidator((Validator)bean);
            return null;
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
