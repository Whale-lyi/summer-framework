package top.whalefall.summerframework.beans.factory.support;

import top.whalefall.summerframework.beans.BeansException;
import top.whalefall.summerframework.beans.factory.DisposableBean;
import top.whalefall.summerframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 单例注册表实现类
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    /**
     * Internal marker for a null singleton object:
     * used as marker value for concurrent Maps (which don't support null values).
     */
    protected static final Object NULL_OBJECT = new Object();

    /**
     * 一级缓存，存放构建完成的bean
     */
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256); //一级缓存

    /**
     * 二级缓存，存放半成品
     */
    protected final Map<String, Object> earlySingletonObjects = new HashMap<>(16); // 二级缓存


    /**
     * 有销毁方法的bean
     */
    private final Map<String, DisposableBean> disposableBeans = new LinkedHashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        Object bean = singletonObjects.get(beanName);
        if (bean == null) {
            bean = earlySingletonObjects.get(beanName);
        }
        return bean;
    }

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        earlySingletonObjects.remove(beanName);
        singletonObjects.put(beanName, singletonObject);
    }

    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeans.put(beanName, bean);
    }

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
