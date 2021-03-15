package com.example.springaop.myaopdemo;

import com.example.springaop.myaop.Advice;


import java.lang.reflect.Method;

public class TimeCsAdvice implements Advice {
    @Override
    public Object invoke(Object target, Method method, Object[] args) throws Throwable {
        // 检测耗时统计
        long stime = System.currentTimeMillis();
        Object re = method.invoke(target,args);
        long useTime = System.currentTimeMillis() - stime;
        System.out.println("记录" + target.getClass().getName() +"." + method.getName() + "耗时" + (useTime/1000) + "秒");
        return re;
    }
}
