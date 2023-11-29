package top.whalefall.summerframework.beans.factory.config;

import lombok.Data;
import top.whalefall.summerframework.beans.PropertyValues;

/**
 * 定义Bean信息的类
 */
@Data
public class BeanDefinition {

    private Class<?> beanClass;

    private PropertyValues propertyValues;

    private String initMethodName;

    private String destroyMethodName;

    public BeanDefinition(Class<?> beanClass) {
        this(beanClass, null, null, null);
    }

    public BeanDefinition(Class<?> beanClass, PropertyValues propertyValues) {
        this(beanClass, propertyValues, null, null);
    }

    public BeanDefinition(Class<?> beanClass, PropertyValues propertyValues, String initMethodName, String destroyMethodName) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
        this.initMethodName = initMethodName;
        this.destroyMethodName = destroyMethodName;
    }
}