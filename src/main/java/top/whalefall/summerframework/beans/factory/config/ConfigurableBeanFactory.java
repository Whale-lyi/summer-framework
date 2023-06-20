package top.whalefall.summerframework.beans.factory.config;

import top.whalefall.summerframework.beans.factory.BeanFactory;

import java.util.List;

/**
 * 提供配置Factory的各种方法
 */
public interface ConfigurableBeanFactory extends BeanFactory {

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    List<BeanPostProcessor> getBeanPostProcessors();

}
