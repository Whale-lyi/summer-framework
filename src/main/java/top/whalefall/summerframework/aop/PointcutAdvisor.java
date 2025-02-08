package top.whalefall.summerframework.aop;

/**
 * @author Liu Yu
 * @description
 * @date 2025-02-07 23:05:42
 */
public interface PointcutAdvisor extends Advisor {

    /**
     * Get the Pointcut that drives this advisor.
     */
    Pointcut getPointcut();

}
