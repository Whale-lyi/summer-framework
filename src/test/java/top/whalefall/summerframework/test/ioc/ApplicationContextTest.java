package top.whalefall.summerframework.test.ioc;

import org.assertj.core.api.Java6Assertions;
import org.junit.Test;
import top.whalefall.summerframework.context.support.ClassPathXmlApplicationContext;
import top.whalefall.summerframework.test.bean.AwareService;
import top.whalefall.summerframework.test.bean.IUserService;
import top.whalefall.summerframework.test.bean.UserService;
import top.whalefall.summerframework.test.common.event.CustomEvent;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Liu Yu
 * @date 2025/2/7
 */
public class ApplicationContextTest {

	@Test
	public void testScanPackage() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring_package_scan.xml");

		IUserService userService = applicationContext.getBean("userService", IUserService.class);
		System.out.println(userService.register("lisi"));
	}

	/**
	 * 测试 FactoryBean
	 */
	@Test
	public void testFactoryBean() {
		// 1.初始化 BeanFactory
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring_factory_bean.xml");
		applicationContext.registerShutdownHook();

		// 2. 调用代理方法
		UserService userService = applicationContext.getBean("userService", UserService.class);
		System.out.println("测试结果：" + userService.queryUserInfo());
	}

	/**
	 * 测试event
	 */
	@Test
	public void test_event() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring_event.xml");
		applicationContext.registerShutdownHook();

		applicationContext.publishEvent(new CustomEvent(applicationContext, 1019129009086763L, "成功了！"));
	}

	/**
	 * 测试初始化和销毁方法
	 */
	@Test
	public void testInitAndDestroyMethod() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring_init_destroy.xml");
		applicationContext.registerShutdownHook();

		UserService userService = applicationContext.getBean("userService", UserService.class);
		System.out.println(userService.queryUserInfo());
	}

	/**
	 * 测试Aware接口
	 */
	@Test
	public void testAwareMethod() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring_aware.xml");
        applicationContext.registerShutdownHook();

        AwareService awareService = applicationContext.getBean("awareService", AwareService.class);
		awareService.sayHello();
        System.out.println("ApplicationContextAware: " + awareService.getApplicationContext());
        System.out.println("BeanFactoryAware: " + awareService.getBeanFactory());
	}

	/**
	 * 测试BeanFactoryPostProcessor和BeanPostProcessor
     */
	@Test
	public void testPostProcessor() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring_post_processor.xml");
		applicationContext.registerShutdownHook();

		UserService userService = applicationContext.getBean("userService", UserService.class);
		System.out.println(userService);
		// company属性在MyBeanFactoryPostProcessor中被修改为字节跳动
		assertThat(userService.getCompany()).isEqualTo("字节跳动");
		// location属性在MyBeanPostProcessor中被修改为北京
		assertThat(userService.getLocation()).isEqualTo("北京");
	}

	/**
	 * 测试单例/多例
     */
	@Test
	public void testProtoType() {
		// 1.初始化 BeanFactory
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring_prototype.xml");
		applicationContext.registerShutdownHook();

		// 2. 获取Bean对象调用方法
		UserService userService01 = applicationContext.getBean("userService", UserService.class);
		UserService userService02 = applicationContext.getBean("userService", UserService.class);

		// 3. 配置 scope="prototype/singleton"
		System.out.println(userService01);
		System.out.println(userService02);
		assertThat(userService01 != userService02).isTrue();
	}

}
