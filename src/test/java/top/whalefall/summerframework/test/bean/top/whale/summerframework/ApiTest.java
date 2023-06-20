package top.whalefall.summerframework.test.bean.top.whale.summerframework;

import org.junit.Test;
import top.whalefall.summerframework.beans.PropertyValue;
import top.whalefall.summerframework.beans.PropertyValues;
import top.whalefall.summerframework.beans.factory.config.BeanDefinition;
import top.whalefall.summerframework.beans.factory.config.BeanReference;
import top.whalefall.summerframework.beans.factory.support.DefaultListableBeanFactory;
import top.whalefall.summerframework.beans.factory.xml.XmlBeanDefinitionReader;
import top.whalefall.summerframework.test.bean.UserDao;
import top.whalefall.summerframework.test.bean.UserService;

public class ApiTest {
    /**
     * 测试解析文件注册
     */
    @Test
    public void testXml() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 读取配置文件&注册Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        // 3. 获取Bean对象调用方法
        UserService userService = beanFactory.getBean("userService", UserService.class);
        userService.queryUserInfo();
    }

    /**
     * 测试注入属性
     */
    @Test
    public void testProperty() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. UserDao 注册
        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        // 3. UserService 设置属性[uId、userDao]
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId", "10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));

        // 4. UserService 注入bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 5. UserService 获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }

}
