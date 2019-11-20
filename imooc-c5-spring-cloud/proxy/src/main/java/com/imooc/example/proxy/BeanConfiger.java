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
import org.springframework.context.annotation.Configuration;

/***
 * 通过新建beanDefinition直接注册上去。通过autowire直接就可以获取。
 * spring是不是非常强大!!!!我也是最近才渐渐明白为什么spring可以作为一个基础框架和其他框架无缝连接。
 * 其中mybatis等第三方集成到spring都是通过FactoryBean、
 */
@Configuration
public class BeanConfiger implements BeanDefinitionRegistryPostProcessor, ApplicationContextAware {
    private ApplicationContext applicationContext;
    private final static Logger LOGGER = LoggerFactory.getLogger(BeanConfiger.class);
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        LOGGER.info("postProcessBeanDefinitionRegistry执行！");
        /*RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(UserDomain.class);
        rootBeanDefinition.setAutowireMode(RootBeanDefinition.AUTOWIRE_BY_TYPE);
        rootBeanDefinition.getPropertyValues().add("name","pepsi");
        registry.registerBeanDefinition("userDomain",rootBeanDefinition);*/

        //使用不同beanDefinition
        Class<?> cls = UserDomain.class;
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(cls);
        GenericBeanDefinition definition = (GenericBeanDefinition) builder.getRawBeanDefinition();
        definition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE);
        definition.getPropertyValues().add("name","pepsi02");
        // 注册bean名,一般为类名首字母小写
        registry.registerBeanDefinition("userDomain", definition);
    }
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        LOGGER.info("postProcessBeanFactory() 执行！");
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}

