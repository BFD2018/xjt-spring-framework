package org.xjt.shuxingtiancong;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.xjt.shuxingtiancong.service.OrderService;
import org.xjt.shuxingtiancong.service.ProductService;
import org.xjt.shuxingtiancong.service.UserService;

@ComponentScan("org.xjt.shuxingtiancong.service")
public class AppConfig {
    @Bean
    public UserService userService(){
        return new UserService();
    };

    @Bean
    public OrderService orderService(){
        return new OrderService();
    };

    @Bean
    public OrderService orderService2(){
        return new OrderService();
    };

    @Bean
    public OrderService orderService3(){
        return new OrderService();
    };

//    @Bean(autowireCandidate = false)        //不会自动导入容器中
    @Bean
    public ProductService productService(){
        return new ProductService();
    };
}
