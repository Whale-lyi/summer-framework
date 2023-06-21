package top.whalefall.summerframework.context.support;


import top.whalefall.summerframework.beans.BeansException;
import top.whalefall.summerframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import top.whalefall.summerframework.beans.factory.support.DefaultListableBeanFactory;

public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {
    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        beanFactory = new DefaultListableBeanFactory();
        loadBeanDefinitions(beanFactory);
    }

    @Override
    protected AbstractAutowireCapableBeanFactory getBeanFactory() {
        return beanFactory;
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);
}
