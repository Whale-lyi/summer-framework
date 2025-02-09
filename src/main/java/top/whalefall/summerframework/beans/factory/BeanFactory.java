package top.whalefall.summerframework.beans.factory;

import top.whalefall.summerframework.beans.BeansException;

/**
 * 定义获取Bean
 */
public interface BeanFactory {

    Object getBean(String name) throws BeansException;

    Object getBean(String name, Object... args) throws BeansException;

    <T> T getBean(String name, Class<T> requiredType) throws BeansException;

    <T> T getBean(Class<T> requiredType) throws BeansException;

    boolean containsBean(String name);

}
