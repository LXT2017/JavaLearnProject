package com.example.springaop.myaop;


import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MikeApplicationContext implements ApplicationContext {

    // 支持高并发
    private Map<String, Class<?>> beanDefinitionMap = new ConcurrentHashMap<>(256);

    // 可以有多个切面，这里用一个来代替模拟
    private Aspect aspect;


    @Override
    public Object getBean(String beanName) throws Throwable{
        // 怎么创建bean对象   用户给出
        // 用户给出bean配置 BeanDefinition
        Object bean = createBeanInstance(beanName);

        //这里需要返回加工后的代理
        bean = proxyEnhance(bean);
        return bean;
    }

    private Object proxyEnhance(Object bean) {
        // 判断是否要增强切面
        if(this.aspect != null && bean.getClass().getName().matches(this.aspect.getPointcut().getClassPattern())){
            return Proxy.newProxyInstance(bean.getClass().getClassLoader(),bean.getClass().getInterfaces(), new AopInvocationHandler(this.aspect,bean));
        }
        return bean;
    }

    private Object createBeanInstance(String beanName) throws Throwable{
        return this.beanDefinitionMap.get(beanName).newInstance();
    }

    @Override
    public void registerBeanDefinition(String beanName, Class<?> beanClass) {
        // 保存下来
        this.beanDefinitionMap.put(beanName, beanClass);
    }

    @Override
    public void setAspect(Aspect aspect) {
        this.aspect = aspect;
    }

}
