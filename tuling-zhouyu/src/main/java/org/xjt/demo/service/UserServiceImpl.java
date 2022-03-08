package org.xjt.demo.service;

import org.xjt.spring.*;

@Component("userService")
public class UserServiceImpl implements BeanNameAware, InitializingBean,UserService{
    @Autowired
    private OrderService orderService;

    private String beanName;

    @Override
    public void test(){
        System.out.println(orderService);
        System.out.println(beanName);
    }

    @Override
    public void setBeanName(String var1) {
        beanName = var1;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化。。。");
    }
}
