package top.whalefall.summerframework.aop;

/**
 * @author Liu Yu
 * @description Filter that restricts matching of a pointcut or introduction to a given set of target classes.
 * @date 2025-02-07 16:57:44
 */
public interface ClassFilter {

    /**
     * Should the pointcut apply to the given interface or target class?
     * @param clazz the candidate target class
     * @return whether the advice should apply to the given target class
     */
    boolean matches(Class<?> clazz);

}
