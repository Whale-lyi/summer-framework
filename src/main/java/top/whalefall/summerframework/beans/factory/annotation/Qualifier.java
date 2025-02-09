package top.whalefall.summerframework.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * @author Liu Yu
 * @date 2025/2/9
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Inherited
@Documented
public @interface Qualifier {

	String value() default "";

}