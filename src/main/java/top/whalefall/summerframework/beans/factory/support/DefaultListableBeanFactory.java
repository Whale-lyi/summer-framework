package top.whalefall.summerframework.beans.factory.support;


import top.whalefall.summerframework.beans.BeansException;
import top.whalefall.summerframework.beans.factory.ConfigurableListableBeanFactory;
import top.whalefall.summerframework.beans.factory.config.BeanDefinition;
import top.whalefall.summerframework.beans.factory.config.BeanFactoryPostProcessor;
import top.whalefall.summerframework.beans.factory.config.BeanPostProcessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 综合所有功能, 主要是对Bean注册后的处理
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {

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
            Class<?> beanClass = beanDefinition.getBeanClass();
            if (type.isAssignableFrom(beanClass)) {
                result.put(beanName, getBean(beanName, type));
            }
        });
        return result;
    }

    @Override
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        List<String> beanNames = new ArrayList<>();
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            Class<?> beanClass = entry.getValue().getBeanClass();
            if (requiredType.isAssignableFrom(beanClass)) {
                beanNames.add(entry.getKey());
            }
        }
        if (beanNames.size() == 1) {
            return getBean(beanNames.get(0), requiredType);
        }

        throw new BeansException(requiredType + "expected single bean but found " + beanNames.size() + ": " + beanNames);
    }

    @Override
    public void preInstantiateSingletons() {
        beanDefinitionMap.keySet().stream()
                .filter(beanName -> {
                    BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
                    return (beanDefinition.getBeanClass() != BeanFactoryPostProcessor.class) &&
                            (beanDefinition.getBeanClass() != BeanPostProcessor.class);
                })
                .forEach(beanName -> {
                    if (beanDefinitionMap.get(beanName).isSingleton()) {
                        getBean(beanName);
                    }
                });
    }
}
