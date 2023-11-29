package top.whalefall.summerframework.context;

import top.whalefall.summerframework.beans.BeansException;

public interface ConfigurableApplicationContext extends ApplicationContext {

    void refresh() throws BeansException;
}
