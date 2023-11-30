package top.whalefall.summerframework.beans.factory.support;

import cn.hutool.core.text.CharSequenceUtil;
import top.whalefall.summerframework.beans.BeansException;
import top.whalefall.summerframework.beans.factory.DisposableBean;
import top.whalefall.summerframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;
    private final String beanName;
    private final String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws BeansException {
        boolean isDisposableBean = bean instanceof DisposableBean;
        // 1. 实现接口 DisposableBean
        if (isDisposableBean) {
            ((DisposableBean) bean).destroy();
        }

        // 2. 配置信息 destroy-method, 避免同时继承自DisposableBean, 且自定义方法与DisposableBean方法同名, 销毁方法执行两次的情况
        if (CharSequenceUtil.isNotEmpty(destroyMethodName) && !(isDisposableBean && "destroy".equals(destroyMethodName))) {
            try {
                Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
                destroyMethod.invoke(bean);
            } catch (NoSuchMethodException e) {
                throw new BeansException("Couldn't find a destroy method named '" + destroyMethodName + "' on bean with name '" + beanName + "'");
            } catch (InvocationTargetException | IllegalAccessException e) {
                throw new BeansException("Couldn't invoke a destroy method named '" + destroyMethodName + "' on bean with name '" + beanName + "'");
            }
        }
    }
}
