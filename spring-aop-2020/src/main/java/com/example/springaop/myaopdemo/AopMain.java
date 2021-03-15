package com.example.springaop.myaopdemo;

import com.example.springaop.myaop.*;
import com.example.springaop.service.SPAService;
import com.example.springaop.service.SpaPrincessA;


public class AopMain {

    public static void main(String[] args) throws Throwable {
        // 用户给出增强逻辑
        Advice advice = new TimeCsAdvice();
        // 给出切面
        // 匹配所有类
        Pointcut pointcut = new Pointcut("com\\.example\\.springaop\\.service\\..*",".*Massage");
        Aspect aspect = new Aspect(pointcut,advice);


        // 用户已经给出
        // 框架把这个切面生效
        // 要使用XXservice对象，不能让用户自己new对象
        // 需要用到一个工厂，对对象进行加工处理
        // IOC是AOP的基石
        ApplicationContext context = new MikeApplicationContext();
        //注册bean定义
        context.registerBeanDefinition("spa",SpaPrincessA.class);
        // xml 或注解方式配置bean

        context.setAspect(aspect);
        SPAService spaService = (SPAService)context.getBean("spa");
        // SPAService spaService = new SpaPrincessA();
        spaService.aromaOilMassage("Mike");

    }
}
