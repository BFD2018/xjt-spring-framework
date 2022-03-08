package org.xjt.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.xjt.spring.service.SomeService;
import org.xjt.spring.utils.SpringContextUtil;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("springbean1.xml");
        SomeService bean1 = applicationContext.getBean("someService", SomeService.class);
        System.out.println(bean1);

        Object someService = SpringContextUtil.getBean("someService");
        System.out.println(someService);

//        ApplicationContext applicationContext1 = new SpringContextUtil().getApplicationContext();
//        System.out.println(applicationContext1);
//        SomeService bean2 = applicationContext1.getBean("someService", SomeService.class);
//        System.out.println(bean2);

    }
}
