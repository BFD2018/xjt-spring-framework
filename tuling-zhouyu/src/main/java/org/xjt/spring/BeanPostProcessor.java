package org.xjt.spring;

import org.springframework.beans.BeansException;

public interface BeanPostProcessor {
    //初始化之前
    Object postProcessBeforeInitialization(Object bean, String beanName);

    /*初始化之后*/
    Object postProcessAfterInitialization(Object bean, String beanName);
}
