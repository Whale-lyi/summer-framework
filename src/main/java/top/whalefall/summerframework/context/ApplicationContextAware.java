package top.whalefall.summerframework.context;

import top.whalefall.summerframework.beans.BeansException;
import top.whalefall.summerframework.beans.factory.Aware;

public interface ApplicationContextAware extends Aware {
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
