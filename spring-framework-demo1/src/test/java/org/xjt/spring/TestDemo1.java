package org.xjt.spring;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.xjt.spring.service.SomeService;

public class TestDemo1 {
    @Test
    public void test01(){
        //1、创建容器对象
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        //2、获取bean
        SomeService bean = (SomeService)applicationContext.getBean("someService");

        //3、执行对象方法
        bean.doSome();
    }
}
