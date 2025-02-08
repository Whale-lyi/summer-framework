package top.whalefall.summerframework.beans.factory.config;

import top.whalefall.summerframework.beans.factory.HierarchicalBeanFactory;
import top.whalefall.summerframework.util.StringValueResolver;

/**
 * 提供配置Factory的各种方法
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {
    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 销毁单例bean
     */
    void destroySingletons();

    void addEmbeddedValueResolver(StringValueResolver valueResolver);

    String resolveEmbeddedValue(String value);

}
