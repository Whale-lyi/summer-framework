package top.whalefall.summerframework.beans.factory.support;


import top.whalefall.summerframework.beans.BeansException;
import top.whalefall.summerframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * bean实例化策略
 */
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, Constructor<?> ctor, Object[] args) throws BeansException;
}
