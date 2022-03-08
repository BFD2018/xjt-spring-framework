package org.xjt.shuxingtiancong.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class UserService {
    @Autowired              //先byType  再byName
    private OrderService orderService;

    @Autowired              //先byType  再byName
    private OrderService orderService2;

    @Autowired
    @Qualifier("orderService3")
    private OrderService orderService4;     //容器中没有名字为 orderService4的对象

    @Autowired
    private ProductService productService;

    public void test(){
        System.out.println("执行了test方法...");
        System.out.println(orderService);
        System.out.println(orderService2);
        System.out.println(orderService4);

        System.out.println(productService);
    }

}
