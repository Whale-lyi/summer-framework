package top.whalefall.summerframework.context.annotation;

import cn.hutool.core.util.ClassUtil;
import top.whalefall.summerframework.beans.factory.config.BeanDefinition;
import top.whalefall.summerframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Liu Yu
 * @description
 * @date 2025-02-08 18:31:17
 */
public class ClassPathScanningCandidateComponentProvider {

    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        // 扫描有top.whalefall.summerframework.stereotype.Component注解的类
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> clazz : classes) {
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            candidates.add(beanDefinition);
        }
        return candidates;
    }

}
