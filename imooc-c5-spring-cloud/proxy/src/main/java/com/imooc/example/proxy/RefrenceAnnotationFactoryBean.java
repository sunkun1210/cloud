package com.imooc.example.proxy;

import com.imooc.example.proxy.service.BizService;
import org.springframework.beans.factory.FactoryBean;

/***
 * c、通过FactoryBean实现接口代理
 * @param <T>
 */
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
