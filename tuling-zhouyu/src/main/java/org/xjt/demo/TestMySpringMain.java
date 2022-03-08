package org.xjt.demo;

import org.xjt.demo.service.UserService;
import org.xjt.spring.XiongConfigApplicationContext;

public class TestMySpringMain {
    public static void main( String[] args ){
        //这是spring框架的基于注解配置的方式 获取IOC容器（我们可以仿照写一个IOC）
        //ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        //ctx.getBean("beanName");     //通过名字 获取bean

        XiongConfigApplicationContext applicationContext = new XiongConfigApplicationContext(AppConfig.class);
        //applicationContext 是IOC容器 以map结构存储 Map<String beanName,Object bean>

//        Object bean1 = applicationContext.getBean("userServiceImpl");       //
//        Object bean2 = applicationContext.getBean("userServiceImpl");
//        Object bean3 = applicationContext.getBean("userServiceImpl");
//        System.out.println(bean1);
//        System.out.println(bean2);
//        System.out.println(bean3);
//
//        System.out.println(applicationContext.getBean("xxxUtil"));
//        System.out.println(applicationContext.getBean("xxxUtil"));

        UserService userServiceImpl = (UserService) applicationContext.getBean("userService");
        userServiceImpl.test();

    }
}
