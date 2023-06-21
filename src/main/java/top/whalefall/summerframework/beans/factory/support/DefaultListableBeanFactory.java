package top.whalefall.summerframework.beans.factory.support;


import top.whalefall.summerframework.beans.BeansException;
import top.whalefall.summerframework.beans.factory.ListableBeanFactory;
import top.whalefall.summerframework.beans.factory.config.BeanDefinition;
import top.whalefall.summerframework.beans.factory.config.BeanFactoryPostProcessor;
import top.whalefall.summerframework.beans.factory.config.BeanPostProcessor;

import java.util.HashMap;
import java.util.Map;

/**
 * 综合所有功能, 主要是对Bean注册后的处理
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry, ListableBeanFactory {

    private final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw new BeansException("No bean named '" + beanName + "' is defined");
        }
        return beanDefinition;
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[0]);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) {
        Map<String, T> result = new HashMap<>();
        beanDefinitionMap.forEach((beanName, beanDefinition) -> {
            Class<?> beanClass = beanDefinition.beanClass();
            if (type.isAssignableFrom(beanClass)) {
                result.put(beanName, getBean(beanName, type));
            }
        });
        return result;
    }

    @Override
    public void preInstantiateSingletons() {
        beanDefinitionMap.keySet().stream()
                .filter(beanName -> {
                    BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
                    return (beanDefinition.beanClass() != BeanFactoryPostProcessor.class) &&
                            (beanDefinition.beanClass() != BeanPostProcessor.class);
                })
                .forEach(this::getBean);
    }
}
