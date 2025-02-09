package top.whalefall.summerframework.test.common;

import top.whalefall.summerframework.beans.factory.FactoryBean;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Liu Yu
 * @description
 * @date 2025-02-09 22:32:04
 */
public class ConvertersFactoryBean implements FactoryBean<Set<?>> {

    @Override
    public Set<?> getObject() throws Exception {
        Set<Object> converters = new HashSet<>();
        StringToLocalDateConverter stringToLocalDateConverter = new StringToLocalDateConverter("yyyy-MM-dd");
        converters.add(stringToLocalDateConverter);
        return converters;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
