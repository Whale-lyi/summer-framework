package top.whalefall.summerframework.beans.factory;

import top.whalefall.summerframework.beans.BeansException;

public interface BeanFactoryAware extends Aware {
    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
