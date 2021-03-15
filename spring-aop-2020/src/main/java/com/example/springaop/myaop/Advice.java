package com.example.springaop.myaop;

import java.lang.reflect.Method;

public interface Advice {

    // 定义一个怎么样的方法
    // 用途？-->用户增强逻辑
    Object invoke(Object target, Method method, Object[] args) throws Throwable;
}
