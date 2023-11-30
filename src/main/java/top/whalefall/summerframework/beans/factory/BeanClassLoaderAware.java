package top.whalefall.summerframework.beans.factory;

import top.whalefall.summerframework.beans.BeansException;

public interface BeanClassLoaderAware extends Aware {
    void setBeanClassLoader(ClassLoader classLoader) throws BeansException;
}
