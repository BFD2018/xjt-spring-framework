package org.xjt.shuxingtiancong;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.xjt.shuxingtiancong.service.UserService;

public class Test1 {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.test();

        System.out.println(userService.getClass().getResource("").getPath());


    }
}
