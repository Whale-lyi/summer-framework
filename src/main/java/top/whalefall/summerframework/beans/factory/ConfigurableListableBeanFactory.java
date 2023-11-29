package top.whalefall.summerframework.beans.factory;

import top.whalefall.summerframework.beans.BeansException;
import top.whalefall.summerframework.beans.factory.config.AutowireCapableBeanFactory;
import top.whalefall.summerframework.beans.factory.config.BeanDefinition;
import top.whalefall.summerframework.beans.factory.config.ConfigurableBeanFactory;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;


}
