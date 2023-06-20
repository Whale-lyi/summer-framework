package top.whalefall.summerframework.beans.factory.config;

/**
 * 定义对单例的定义及获取
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

    void addSingleton(String beanName, Object singletonObject);
}
