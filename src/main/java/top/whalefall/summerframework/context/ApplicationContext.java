package top.whalefall.summerframework.context;

import top.whalefall.summerframework.beans.factory.ListableBeanFactory;

public interface ApplicationContext extends ListableBeanFactory {
    /**
     * 刷新容器
     */
    void refresh();
}
