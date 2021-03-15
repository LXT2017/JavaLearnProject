package com.example.springaop.myaop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class AopInvocationHandler implements InvocationHandler {

    private Aspect aspect;

    private Object bean;

    public AopInvocationHandler(Aspect aspect, Object bean) {
        this.aspect = aspect;
        this.bean = bean;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //应用增强逻辑
        //判断当前方法是否为用户要增强的
        if(method.getName().matches(aspect.getPointcut().getMethodPattern())){
            return aspect.getAdvice().invoke(bean,method,args);
        }
        return method.invoke(bean,args);
    }
}
