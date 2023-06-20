package top.whalefall.summerframework.beans.factory.config;

import lombok.Data;
import top.whalefall.summerframework.beans.PropertyValues;

/**
 * 定义Bean信息的类
 */
@Data
public class BeanDefinition {

    /**
     * Bean类型
     */
    private Class<?> beanClass;
    /**
     * Bean属性
     */
    private PropertyValues propertyValues;

    public BeanDefinition(Class<?> beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class<?> beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }
}
