package top.whalefall.summerframework.test.aop;

import org.junit.Test;
import top.whalefall.summerframework.aop.aspectj.AspectJExpressionPointcut;
import top.whalefall.summerframework.test.bean.AwareService;

import java.lang.reflect.Method;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Liu Yu
 * @date 2025/2/7
 */
public class AspectTest {



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
