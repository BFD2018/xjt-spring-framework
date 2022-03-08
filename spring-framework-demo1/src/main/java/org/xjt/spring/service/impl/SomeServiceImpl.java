package org.xjt.spring.service.impl;

import org.xjt.spring.service.SomeService;

public class SomeServiceImpl implements SomeService {
    @Override
    public void doSome() {
        System.out.println("zhixiing le dosome ...");
    }

    @Override
    public void doOther() {
        System.out.println("zhixiing le doOther ...");
    }
}
