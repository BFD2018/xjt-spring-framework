package org.xjt.myAop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDemo {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("aoptest.xml");
        UserService userSeviceImpl = context.getBean("userSevice", UserService.class);

        userSeviceImpl.add();
    }
}
