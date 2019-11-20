package com.imooc.example.proxy;

import org.springframework.beans.factory.FactoryBean;

public class RefrenceAnnotationFactoryBean<T> implements FactoryBean<T> {

    public RefrenceAnnotationFactoryBean(){}

    @Override
    public T getObject() throws Exception {
        return (T) InterfaceProxy.newInstance(BizService.class);
    }

    @Override
    public Class<?> getObjectType() {
        return BizService.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
