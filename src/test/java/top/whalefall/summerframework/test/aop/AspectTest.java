package top.whalefall.summerframework.test.aop;

import org.junit.Test;
import top.whalefall.summerframework.aop.AdvisedSupport;
import top.whalefall.summerframework.aop.TargetSource;
import top.whalefall.summerframework.aop.aspectj.AspectJExpressionPointcut;
import top.whalefall.summerframework.aop.framework.CglibAopProxy;
import top.whalefall.summerframework.aop.framework.JdkDynamicAopProxy;
import top.whalefall.summerframework.test.bean.AwareService;
import top.whalefall.summerframework.test.bean.IUserService;
import top.whalefall.summerframework.test.bean.UserService;
import top.whalefall.summerframework.test.common.UserServiceInterceptor;

import java.lang.reflect.Method;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Liu Yu
 * @date 2025/2/7
 */
public class AspectTest {

	@Test
	public void testDynamicProxy() {
		// 目标对象
		IUserService userService = new UserService();

		// 组装代理信息
		AdvisedSupport advisedSupport = new AdvisedSupport();
		advisedSupport.setTargetSource(new TargetSource(userService));
		advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
		advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* top.whalefall.summerframework.test.bean.IUserService.*(..))"));

		// 代理对象(JdkDynamicAopProxy)
		IUserService jdkProxy = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
		// 测试调用
		System.out.println("测试结果：" + jdkProxy.register("jack"));

		// 代理对象(Cglib2AopProxy)
		IUserService cglibProxy = (IUserService) new CglibAopProxy(advisedSupport).getProxy();
		// 测试调用
		System.out.println("测试结果：" + cglibProxy.register("花花"));
	}


	/**
	 * 测试切点表达式匹配
	 * @throws Exception
	 */
	@Test
	public void testPointcutExpression() throws Exception {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* top.whalefall.summerframework.test.bean.AwareService.*(..))");
		Class<AwareService> clazz = AwareService.class;
		Method method = clazz.getDeclaredMethod("sayHello");

		assertThat(pointcut.matches(clazz)).isTrue();
		assertThat(pointcut.matches(method, clazz)).isTrue();
	}
}
