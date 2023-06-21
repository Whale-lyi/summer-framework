package top.whalefall.summerframework.beans.factory;

import java.util.Map;

/**
 * 根据各种条件获取Bean的配置清单
 */
public interface ListableBeanFactory extends BeanFactory {
    <T> Map<String, T> getBeansOfType(Class<T> type);
}
