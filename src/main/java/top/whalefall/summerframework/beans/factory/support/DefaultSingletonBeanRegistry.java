package top.whalefall.summerframework.beans.factory.support;

import top.whalefall.summerframework.beans.BeansException;
import top.whalefall.summerframework.beans.factory.DisposableBean;
import top.whalefall.summerframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * 单例注册表实现类
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    /**
     * 单例 Bean 对象
     */
    private final Map<String, Object> singletonObjects = new HashMap<>();

    /**
     * 有销毁方法的bean
     */
    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }

    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeans.put(beanName, bean);
    }

    @Override
    public void destroySingletons() {
        Object[] disposableBeanNames = disposableBeans.keySet().toArray();
        for (Object beanName : disposableBeanNames) {
            DisposableBean disposableBean = disposableBeans.remove((String) beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }
}
