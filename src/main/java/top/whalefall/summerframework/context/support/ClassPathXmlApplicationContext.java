package top.whalefall.summerframework.context.support;

import lombok.Getter;
import lombok.NoArgsConstructor;
import top.whalefall.summerframework.beans.BeansException;
import top.whalefall.summerframework.beans.factory.support.DefaultListableBeanFactory;
import top.whalefall.summerframework.beans.factory.xml.XmlBeanDefinitionReader;

@Getter
@NoArgsConstructor
public class ClassPathXmlApplicationContext extends AbstractRefreshableApplicationContext {

    private String[] configLocations;

    /**
     * 从 XML 中加载 BeanDefinition，并刷新上下文.
     *
     * @param configLocations
     * @throws BeansException
     */
    public ClassPathXmlApplicationContext(String[] configLocations) throws BeansException {
        this.configLocations = configLocations;
        refresh();
    }

    public ClassPathXmlApplicationContext(String configLocation) throws BeansException {
        this(new String[]{configLocation});
    }

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        if (configLocations != null) {
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }
}
