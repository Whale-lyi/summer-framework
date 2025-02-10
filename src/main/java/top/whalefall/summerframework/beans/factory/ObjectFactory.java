package top.whalefall.summerframework.beans.factory;

import top.whalefall.summerframework.beans.BeansException;

/**
 * @author Liu Yu
 * @description
 * @date 2025-02-09 23:53:54
 */
public interface ObjectFactory<T> {

    T getObject() throws BeansException;

}
