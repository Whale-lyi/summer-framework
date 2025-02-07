package top.whalefall.summerframework.aop;

import java.lang.reflect.Method;

/**
 * @author Liu Yu
 * @description Part of a {@link Pointcut}: Checks whether the target method is eligible for advice.
 * @date 2025-02-07 16:57:52
 */
public interface MethodMatcher {

    boolean matches(Method method, Class<?> targetClass);

}
