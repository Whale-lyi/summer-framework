package top.whalefall.summerframework.beans.factory.config;

import top.whalefall.summerframework.beans.BeansException;

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

}
