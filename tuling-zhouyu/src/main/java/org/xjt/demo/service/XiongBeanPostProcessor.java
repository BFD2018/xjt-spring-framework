package org.xjt.demo.service;

import org.xjt.spring.Component;
import org.xjt.spring.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Component("xinogBeanPostProcessor")
public class XiongBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println("初始化前。。。"+beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("初始化后---"+beanName);
        if(beanName.equals("userService")){
            Object proxyInstance = Proxy.newProxyInstance(XiongBeanPostProcessor.class.getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("代理逻辑。。。");
                    Object invoke = method.invoke(bean, args);
                    return invoke;
                }
            });

            return proxyInstance;
        }
        return bean;
    }
}
