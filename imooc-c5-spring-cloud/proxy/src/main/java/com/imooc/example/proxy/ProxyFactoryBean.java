package com.imooc.example.proxy;

import com.imooc.example.proxy.service.BizService;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/***
 * c、通过FactoryBean实现接口代理
 * @param <T>
 */
public class ProxyFactoryBean<T> implements FactoryBean<T> , InvocationHandler {

    public ProxyFactoryBean(){}
    private Class serviceInterface=BizService.class;
    @Override
    public T getObject() throws Exception {
            return (T) Proxy.newProxyInstance(this.serviceInterface.getClassLoader(), new Class[]{this.serviceInterface}, this);
    }

    @Override
    public Class<?> getObjectType() {
        return BizService.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("ObjectProxy execute:" + method.getName());
        String param = Arrays.toString(args);
        return "动态代理invoke方法返回值="+param;
    }
}
