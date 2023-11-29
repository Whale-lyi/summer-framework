package top.whalefall.summerframework.beans.factory.config;

import top.whalefall.summerframework.beans.factory.BeanFactory;
import top.whalefall.summerframework.beans.factory.HierarchicalBeanFactory;

import java.util.List;

/**
 * 提供配置Factory的各种方法
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {
    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

}
