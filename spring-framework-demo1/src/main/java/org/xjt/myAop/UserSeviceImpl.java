package org.xjt.myAop;

public class UserSeviceImpl implements UserService{
    @Override
    public void add() {
        System.out.println("执行了UserSeviceImpl的 add 方法。。。");
    }

    @Override
    public void delete() {
        System.out.println("执行了UserSeviceImpl的 delete 方法。。。");
    }

    @Override
    public void update() {
        System.out.println("执行了UserSeviceImpl的 update 方法。。。");
    }

    @Override
    public void select() {
        System.out.println("执行了UserSeviceImpl的 select 方法。。。");
    }
}
