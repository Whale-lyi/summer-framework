package top.whalefall.summerframework.aop.framework;

/**
 * @author Liu Yu
 * @description AOP 代理的抽象
 * @date 2025-02-07 19:43:00
 * Delegate interface for a configured AOP proxy, allowing for the creation of actual proxy objects.
 */
public interface AopProxy {

    Object getProxy();

}
