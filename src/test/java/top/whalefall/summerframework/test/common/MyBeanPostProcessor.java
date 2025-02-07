package top.whalefall.summerframework.test.common;

import top.whalefall.summerframework.beans.BeansException;
import top.whalefall.summerframework.beans.factory.config.BeanPostProcessor;
import top.whalefall.summerframework.test.bean.UserService;

public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("MyBeanPostProcessor#postProcessBeforeInitialization, beanName: " + beanName);
        if ("userService".equals(beanName)) {
            UserService userService = (UserService) bean;
            userService.setLocation("北京");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("MyBeanPostProcessor#postProcessAfterInitialization, beanName: " + beanName);
        return bean;
    }

}
