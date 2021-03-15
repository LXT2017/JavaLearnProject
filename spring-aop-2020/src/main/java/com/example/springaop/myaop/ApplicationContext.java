package com.example.springaop.myaop;

public interface ApplicationContext {

    Object getBean(String beanName) throws Throwable;

    void registerBeanDefinition(String beanName, Class<?> beanClass);


    void setAspect(Aspect aspect);
}
