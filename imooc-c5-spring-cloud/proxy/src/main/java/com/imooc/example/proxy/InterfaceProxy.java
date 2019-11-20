package com.imooc.example.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class InterfaceProxy implements InvocationHandler {


    public String invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("ObjectProxy execute:" + method.getName());
        String methodName = method.getName();
        String param = Arrays.toString(args);
        return "pepsi is param="+param;
    }
    public static <T> T newInstance(Class<T> innerInterface) {
        ClassLoader classLoader = innerInterface.getClassLoader();
        Class[] interfaces = new Class[] { innerInterface };
        InterfaceProxy proxy = new InterfaceProxy();
        return (T) Proxy.newProxyInstance(classLoader, interfaces, proxy);
    }
}

