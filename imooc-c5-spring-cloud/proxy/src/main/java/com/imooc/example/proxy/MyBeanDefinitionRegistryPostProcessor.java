package com.imooc.example.proxy;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/***
 *  a、先是通过BeanDefinitionRegistryPostProcessor 进行拦截
 *   * 通过新建beanDefinition直接注册上去（Service的代理）。通过autowire直接就可以获取。
 *  * spring是不是非常强大!!!!我也是最近才渐渐明白为什么spring可以作为一个基础框架和其他框架无缝连接。
 *  * 其中mybatis等第三方集成到spring都是通过FactoryBean、
 */
@Component
public class MyBeanDefinitionRegistryPostProcessor implements ApplicationContextAware, BeanDefinitionRegistryPostProcessor {

    private ApplicationContext applicationContext;

    private Logger LOGGER = LoggerFactory.getLogger(MyBeanDefinitionRegistryPostProcessor.class);


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        LOGGER.info("postProcessBeanFactory() beanDefinition的个数=====>"+beanFactory.getBeanDefinitionCount());
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        LOGGER.info("postProcessBeanDefinitionRegistry() beanDefinitionName=====>"+registry.getBeanDefinitionNames().toString());
        // 需要被代理的接口
        MyClassPathBeanDefinitionScanner annotationScanner = new MyClassPathBeanDefinitionScanner(registry);
        annotationScanner.setResourceLoader(applicationContext);
        annotationScanner.scan("com.imooc.example.proxy.service");
    }
}
