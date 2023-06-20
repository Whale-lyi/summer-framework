package top.whalefall.summerframework.beans.factory.support;


import top.whalefall.summerframework.beans.BeansException;
import top.whalefall.summerframework.core.io.Resource;
import top.whalefall.summerframework.core.io.ResourceLoader;

/**
 * 定义资源文件的读取, 并转换为BeanDefinition
 */
public interface BeanDefinitionReader {
    BeanDefinitionRegistry getRegistry();
    ResourceLoader getResourceLoader();
    void loadBeanDefinitions(Resource resource) throws BeansException;
    void loadBeanDefinitions(Resource... resources) throws BeansException;
    void loadBeanDefinitions(String location) throws BeansException;
    void loadBeanDefinitions(String... locations) throws BeansException;
}
