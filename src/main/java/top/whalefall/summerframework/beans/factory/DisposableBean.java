package top.whalefall.summerframework.beans.factory;

import top.whalefall.summerframework.beans.BeansException;

public interface DisposableBean {
    void destroy() throws BeansException;
}
