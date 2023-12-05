package top.whalefall.summerframework.context;

import top.whalefall.summerframework.beans.factory.HierarchicalBeanFactory;
import top.whalefall.summerframework.beans.factory.ListableBeanFactory;
import top.whalefall.summerframework.core.io.ResourceLoader;

public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {
}
