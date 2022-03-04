package org.demo.plugin;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;

import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @author Shawn
 * @version 1.0
 * @description: TODO
 * @date 2022/2/28 21:22
 */
@Intercepts({@Signature(
        type= Executor.class,
        method = "update",
        args = {MappedStatement.class,Object.class})})
public class ExamplePlugin implements Interceptor {
    //自定义属性
    private int number;

    // 当执行目标方法时会被方法拦截
    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget(); //被代理对象
        Method method = invocation.getMethod(); //代理方法
        Object[] args = invocation.getArgs(); //方法参数
        // do something ...... 方法拦截前执行代码块
        Object result = invocation.proceed();
        // do something .......方法拦截后执行代码块
        return result;
    }
    // 生成代理对象，可自定义生成代理对象，这样就无需配置@Intercepts注解。另外需要自行判断是否为拦截目标接口。
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);// 调用通用插件代理生成机器
    }

    //配置属性
    public void setProperties(Properties properties) {
        this.number = Integer.parseInt(properties.getProperty("number", String.valueOf(100)));
    }
}
