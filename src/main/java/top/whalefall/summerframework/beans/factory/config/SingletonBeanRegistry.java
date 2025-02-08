package top.whalefall.summerframework.beans.factory.config;

/**
 * 定义对单例的操作
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

    void registerSingleton(String beanName, Object singletonObject);

}
