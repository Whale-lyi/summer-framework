package top.whalefall.summerframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.text.CharSequenceUtil;
import top.whalefall.summerframework.beans.BeansException;
import top.whalefall.summerframework.beans.PropertyValue;
import top.whalefall.summerframework.beans.PropertyValues;
import top.whalefall.summerframework.beans.factory.DisposableBean;
import top.whalefall.summerframework.beans.factory.InitializingBean;
import top.whalefall.summerframework.beans.factory.config.AutowireCapableBeanFactory;
import top.whalefall.summerframework.beans.factory.config.BeanDefinition;
import top.whalefall.summerframework.beans.factory.config.BeanPostProcessor;
import top.whalefall.summerframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 综合AbstractBeanFactory, 并对接口AutowireCapableBeanFactory进行实现
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private final InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) {
        try {
            Object bean = createBeanInstance(beanDefinition, args);
            // 填充属性
            applyPropertyValue(beanName, bean, beanDefinition);
            // 执行 Bean 的初始化方法和 BeanPostProcessor 的前置和后置处理方法
            bean = initializeBean(beanName, bean, beanDefinition);
            // 注册实现了 DisposableBean 接口的 bean
            registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);
            addSingleton(beanName, bean);
            return bean;
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
    }

    protected void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {
        if (bean instanceof DisposableBean || CharSequenceUtil.isNotEmpty(beanDefinition.getDestroyMethodName())) {
            registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
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
        try {
            invokeInitMethods(beanName, wrappedBean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Invocation of init method of bean[" + beanName + "] failed", e);
        }
        // 3. 执行 BeanPostProcessor After 处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
        return wrappedBean;
    }

    private void invokeInitMethods(String beanName, Object bean, BeanDefinition beanDefinition) {
        boolean isInitializingBean = bean instanceof InitializingBean;
        // 1. 实现 InitializingBean 接口方式
        if (isInitializingBean) {
            ((InitializingBean) bean).afterPropertiesSet();
        }
        // 2. 配置 init-method 方式
        String initMethodName = beanDefinition.getInitMethodName();
        if (CharSequenceUtil.isNotEmpty(initMethodName) && !(isInitializingBean && "afterPropertiesSet".equals(initMethodName))) {
            try {
                Method initMethod = beanDefinition.getBeanClass().getMethod(initMethodName);
                initMethod.invoke(bean);
            } catch (NoSuchMethodException e) {
                throw new BeansException("Could not find an init method named '" + initMethodName + "' on bean with name '" + beanName + "'");
            } catch (InvocationTargetException | IllegalAccessException e) {
                throw new BeansException("Could not invoke an init method named '" + initMethodName + "' on bean with name '" + beanName + "'", e);
            }
        }
    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessBeforeInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessAfterInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }

}
