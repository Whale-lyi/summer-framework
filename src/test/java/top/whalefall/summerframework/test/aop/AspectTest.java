package top.whalefall.summerframework.test.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Test;
import top.whalefall.summerframework.aop.AdvisedSupport;
import top.whalefall.summerframework.aop.ClassFilter;
import top.whalefall.summerframework.aop.TargetSource;
import top.whalefall.summerframework.aop.aspectj.AspectJExpressionPointcut;
import top.whalefall.summerframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import top.whalefall.summerframework.aop.framework.ProxyFactory;
import top.whalefall.summerframework.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import top.whalefall.summerframework.context.support.ClassPathXmlApplicationContext;
import top.whalefall.summerframework.test.bean.AwareService;
import top.whalefall.summerframework.test.bean.IUserService;
import top.whalefall.summerframework.test.bean.UserService;
import top.whalefall.summerframework.test.common.UserServiceBeforeAdvice;
import top.whalefall.summerframework.test.common.UserServiceInterceptor;

import java.lang.reflect.Method;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Liu Yu
 * @date 2025/2/7
 */
public class AspectTest {

	/**
	 * 测试AOP融入Bean生命周期
	 */
	@Test
	public void testAutoProxy() throws Exception {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring_auto_proxy.xml");
		applicationContext.registerShutdownHook();

		IUserService userService = applicationContext.getBean("userService", IUserService.class);
		System.out.println("测试结果：" + userService.register("zhangsan"));
	}

	/**
	 * 测试PointcutAdvisor
	 */
	@Test
	public void testAdvisor() throws Exception {
		IUserService userService = new UserService();

		//Advisor是Pointcut和Advice的组合
		AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
		advisor.setExpression("execution(* top.whalefall.summerframework.test.bean.IUserService.*(..))");
		MethodBeforeAdviceInterceptor methodInterceptor = new MethodBeforeAdviceInterceptor(new UserServiceBeforeAdvice());
		advisor.setAdvice(methodInterceptor);

		ClassFilter classFilter = advisor.getPointcut().getClassFilter();
		if (classFilter.matches(userService.getClass())) {
			AdvisedSupport advisedSupport = new AdvisedSupport();

			TargetSource targetSource = new TargetSource(userService);
			advisedSupport.setTargetSource(targetSource);
			advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
			advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
//			advisedSupport.setProxyTargetClass(true);   //JDK or CGLIB

			IUserService proxy = (IUserService) new ProxyFactory(advisedSupport).getProxy();
			System.out.println(proxy.register("whale"));
		}
	}

	/**
	 * 测试BeforeAdvice
	 * @throws Exception
	 */
	@Test
	public void testBeforeAdvice() throws Exception {
		// 目标对象
		IUserService userService = new UserService();

		//设置BeforeAdvice
		UserServiceBeforeAdvice beforeAdvice = new UserServiceBeforeAdvice();
		MethodBeforeAdviceInterceptor methodInterceptor = new MethodBeforeAdviceInterceptor(beforeAdvice);

		// 组装代理信息
		AdvisedSupport advisedSupport = new AdvisedSupport();
		advisedSupport.setMethodInterceptor(methodInterceptor);
		advisedSupport.setTargetSource(new TargetSource(userService));
		advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* top.whalefall.summerframework.test.bean.IUserService.*(..))"));

		IUserService jdkProxy = (IUserService) new ProxyFactory(advisedSupport).getProxy();
		// 测试调用
		System.out.println("测试结果：" + jdkProxy.register("jack"));
	}

	/**
	 * 测试动态代理和代理工厂
	 */
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
		advisedSupport.setProxyTargetClass(false);
		IUserService jdkProxy = (IUserService) new ProxyFactory(advisedSupport).getProxy();
		// 测试调用
		System.out.println("测试结果：" + jdkProxy.register("jack"));

		// 代理对象(Cglib2AopProxy)
		advisedSupport.setProxyTargetClass(true);
		IUserService cglibProxy = (IUserService) new ProxyFactory(advisedSupport).getProxy();
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
