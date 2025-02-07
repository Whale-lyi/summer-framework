package top.whalefall.summerframework.aop.framework.adapter;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import top.whalefall.summerframework.aop.MethodBeforeAdvice;

/**
 * @author Liu Yu
 * @description
 * @date 2025-02-07 22:46:11
 */
public class MethodBeforeAdviceInterceptor implements MethodInterceptor {

    private final MethodBeforeAdvice advice;

    public MethodBeforeAdviceInterceptor(MethodBeforeAdvice advice) {
        this.advice = advice;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        //在执行被代理方法之前，先执行before advice操作
        this.advice.before(invocation.getMethod(), invocation.getArguments(), invocation.getThis());
        return invocation.proceed();
    }
}
