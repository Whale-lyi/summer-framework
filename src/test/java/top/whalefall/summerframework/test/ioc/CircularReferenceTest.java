package top.whalefall.summerframework.test.ioc;

import org.junit.Test;
import top.whalefall.summerframework.context.support.ClassPathXmlApplicationContext;
import top.whalefall.summerframework.test.bean.A;
import top.whalefall.summerframework.test.bean.B;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * @author Liu Yu
 * @description
 * @date 2025-02-09 22:53:45
 */
public class CircularReferenceTest {

    @Test
    public void testCircularReferenceWithProxyBean() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring_circular_reference_with_proxy_bean.xml");
        A a = applicationContext.getBean("a", A.class);
        B b = applicationContext.getBean("b", B.class);
        assertThat(a.getB() == b).isTrue();
        assertThat(b.getA() == a).isTrue();
        a.func();
    }

    @Test
    public void testCircularReference() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring_circular_reference.xml");
        A a = applicationContext.getBean("a", A.class);
        B b = applicationContext.getBean("b", B.class);
        assertThat(a.getB() == b).isTrue();
        assertThat(b.getA() == a).isTrue();
    }

}
