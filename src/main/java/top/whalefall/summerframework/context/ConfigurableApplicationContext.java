package top.whalefall.summerframework.context;

import top.whalefall.summerframework.beans.BeansException;

public interface ConfigurableApplicationContext extends ApplicationContext {

    void refresh() throws BeansException;

    /**
     * 注册虚拟机钩子
     */
    void registerShutdownHook();

    void close();
}
