package top.whalefall.summerframework.beans.factory.config;


import top.whalefall.summerframework.beans.factory.BeanFactory;

/**
 * 提供创建bean、自动注入、初始化、以及应用bean的后处理器
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     * 执行 BeanPostProcessors 接口实现类的 postProcessBeforeInitialization 方法
     * @param existingBean
     * @param beanName
     * @return
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName);

    /**
     * 执行 BeanPostProcessors 接口实现类的 postProcessorsAfterInitialization 方法
     *
     * @param existingBean
     * @param beanName
     * @return
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName);

    /**
     * 初始化单例对象
     */
    void preInstantiateSingletons();

}
