package top.whalefall.summerframework.beans.factory;

import top.whalefall.summerframework.beans.BeansException;

public interface BeanNameAware extends Aware {
    void setBeanName(String name) throws BeansException;
}
