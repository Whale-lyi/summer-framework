package top.whalefall.summerframework.test.common;


import top.whalefall.summerframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author Liu Yu
 * @date 2025/2/9
 */
public class ABeforeAdvice implements MethodBeforeAdvice {
	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {

	}
}
