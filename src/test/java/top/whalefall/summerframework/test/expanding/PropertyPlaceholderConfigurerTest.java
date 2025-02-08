package top.whalefall.summerframework.test.expanding;

import org.junit.Test;
import top.whalefall.summerframework.context.support.ClassPathXmlApplicationContext;
import top.whalefall.summerframework.test.bean.IUserService;
import top.whalefall.summerframework.test.bean.UserService;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Liu Yu
 * @description
 * @date 2025-02-08 14:23:31
 */
public class PropertyPlaceholderConfigurerTest {

    @Test
    public void test() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring_placeholder.xml");
        applicationContext.registerShutdownHook();

        UserService userService = applicationContext.getBean("userService", UserService.class);
        System.out.println(userService);
        assertThat(userService.getLocation()).isEqualTo("Shang Hai");
        assertThat(userService.getCompany()).isEqualTo("Tencent");
    }
}
