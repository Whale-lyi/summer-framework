package top.whalefall.summerframework.stereotype;

import java.lang.annotation.*;

/**
 * @author Liu Yu
 * @description
 * @date 2025-02-08 18:04:41
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {

    String value() default "";

}
