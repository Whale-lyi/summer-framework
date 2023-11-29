package top.whalefall.summerframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import top.whalefall.summerframework.beans.BeansException;
import top.whalefall.summerframework.beans.PropertyValue;
import top.whalefall.summerframework.beans.PropertyValues;
import top.whalefall.summerframework.beans.factory.config.AutowireCapableBeanFactory;
import top.whalefall.summerframework.beans.factory.config.BeanDefinition;
import top.whalefall.summerframework.beans.factory.config.BeanPostProcessor;
import top.whalefall.summerframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 综合AbstractBeanFactory, 并对接口AutowireCapableBeanFactory进行实现
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private final InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) {
        try {
            Object bean = createBeanInstance(beanDefinition, args);
            // 填充属性
            applyPropertyValue(beanName, bean, beanDefinition);
            // 执行 Bean 的初始化方法和 BeanPostProcessor 的前置和后置处理方法
            bean = initializeBean(beanName, bean, beanDefinition);

            addSingleton(beanName, bean);
            return bean;
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
    }

    /**
     * 选择构造函数创建Bean实例
     * @param beanDefinition
     * @param args
     * @return
     */
    protected Object createBeanInstance(BeanDefinition beanDefinition, Object[] args) {
        Constructor<?> constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        if (args != null) {
            // 处理BeanReference
            IntStream.range(0, args.length)
                    .filter(i -> args[i] instanceof BeanReference)
                    .forEach(i -> {
                        BeanReference beanReference = (BeanReference) args[i];
                        args[i] = getBean(beanReference.getBeanName());
                    });
            // 查找符合条件的构造函数
            constructorToUse = Arrays.stream(declaredConstructors)
                    .filter(ctor -> ctor.getParameterTypes().length == args.length)
                    .filter(ctor -> {
                        for (int i = 0; i < args.length; i++) {
                            if (args[i] != null && !ctor.getParameterTypes()[i].isAssignableFrom(args[i].getClass())) {
                                return false;
                            }
                        }
                        return true;
                    })
                    .findFirst()
                    .orElse(null);
        }
        return instantiationStrategy.instantiate(beanDefinition, constructorToUse, args);
    }

    /**
     * Bean 属性填充
     * @param beanName
     * @param bean
     * @param beanDefinition
     */
    protected void applyPropertyValue(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();
                // A 依赖 B, 获取 B 实例
                if (value instanceof BeanReference beanReference) {
                    value = getBean(beanReference.getBeanName());
                }
                // 属性填充
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeansException("Error setting property values：" + beanName);
        }
    }

    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        // 1. 执行 BeanPostProcessor Before 处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);
        // 2. 执行 Bean 初始化方法
        invokeInitMethods(beanName, wrappedBean, beanDefinition);
        // 3. 执行 BeanPostProcessor After 处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
        return wrappedBean;
    }

    private void invokeInitMethods(String beanName, Object wrappedBean, BeanDefinition beanDefinition) {

    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessBeforeInitialization(result, beanName);
            if (current == null) return result;
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessAfterInitialization(result, beanName);
            if (current == null) return result;
            result = current;
        }
        return result;
    }

}
