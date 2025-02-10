package top.whalefall.summerframework.beans.factory.config;

import top.whalefall.summerframework.beans.BeansException;
import top.whalefall.summerframework.beans.PropertyValues;

/**
 * @author Liu Yu
 * @description
 * @date 2025-02-07 23:40:38
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    /**
     * 在 Bean 实例化之前执行
     *
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;

    /**
     * 在 Bean 实例化之后执行
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException;

    /**
     * bean实例化之后，设置属性之前执行
     *
     * @param pvs
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException;

}
