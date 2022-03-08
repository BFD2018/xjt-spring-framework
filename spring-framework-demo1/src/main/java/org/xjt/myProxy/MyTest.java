package org.xjt.myProxy;

import org.junit.Test;
import org.xjt.myProxy.service.UserService;
import org.xjt.myProxy.service.impl.UserServiceImpl;
import org.xjt.spring.service.SomeService;
import org.xjt.spring.service.impl.SomeServiceImpl;

import java.lang.reflect.Proxy;

public class MyTest {
    public static void main(String[] args) {
        SomeServiceImpl target = new SomeServiceImpl();
        MyInvocationHandler handler = new MyInvocationHandler(target);

        SomeService proxy = (SomeService) Proxy.newProxyInstance(
//                handler.getClass().getClassLoader(),
                ClassLoader.getSystemClassLoader(),
                target.getClass().getInterfaces(),
                handler
        );

        proxy.doSome();
        proxy.doOther();
    }

    @Test
    public void testClassLoader(){
        //理解类加载器
        ClassLoader c = ClassLoader.getSystemClassLoader();
        System.out.println(c);       //jdk.internal.loader.ClassLoaders$AppClassLoader@3fee733d

        SomeServiceImpl target = new SomeServiceImpl();
        MyInvocationHandler handler = new MyInvocationHandler(target);
        ClassLoader c1 = target.getClass().getClassLoader();        //jdk.internal.loader.ClassLoaders$AppClassLoader@3fee733d
        ClassLoader c2 = handler.getClass().getClassLoader();   //jdk.internal.loader.ClassLoaders$AppClassLoader@3fee733d
        ClassLoader c3 = this.getClass().getClassLoader();  //jdk.internal.loader.ClassLoaders$AppClassLoader@3fee733d
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(ClassLoader.getSystemClassLoader());     //jdk.internal.loader.ClassLoaders$AppClassLoader@3fee733d
        System.out.println(c1 == c2);
        System.out.println(c1 == c3);
        System.out.println(c1 == c);


    }

    @Test
    public void testDynamicProxy(){
        UserService userService = new UserServiceImpl();
        MyProxyInvocationHandler invocationHandler = new MyProxyInvocationHandler();
        invocationHandler.setTarget(userService);       //设置代理对象

        //动态生成代理类
        UserService proxy = (UserService)invocationHandler.getProxy();
        proxy.add();
    }

}
