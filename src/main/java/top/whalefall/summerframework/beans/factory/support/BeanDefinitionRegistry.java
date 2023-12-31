package top.whalefall.summerframework.beans.factory.support;


import top.whalefall.summerframework.beans.BeansException;
import top.whalefall.summerframework.beans.factory.config.BeanDefinition;

/**
 * BeanDefinition注册表接口, 对BeanDefinition各种增删改查操作
 */
public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    boolean containsBeanDefinition(String beanName);

    String[] getBeanDefinitionNames();

}
