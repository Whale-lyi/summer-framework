package top.whalefall.summerframework.test.common;

import top.whalefall.summerframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author Liu Yu
 * @description
 * @date 2025-02-07 22:48:49
 */
public class UserServiceBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("BeforeAdvice拦截方法: " + method.getName());
    }
}
