package top.whalefall.summerframework.beans.factory.config;

import top.whalefall.summerframework.beans.PropertyValues;

/**
 * 定义Bean信息的类
 */
public record BeanDefinition(Class<?> beanClass, PropertyValues propertyValues) {

    public BeanDefinition(Class<?> beanClass) {
        this(beanClass, null);
    }

    public BeanDefinition(Class<?> beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }
}
