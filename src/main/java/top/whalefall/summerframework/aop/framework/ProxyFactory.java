package top.whalefall.summerframework.aop.framework;

import top.whalefall.summerframework.aop.AdvisedSupport;

/**
 * @author Liu Yu
 * @description
 * @date 2025-02-07 20:47:27
 */
public class ProxyFactory {

    private final AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    private AopProxy createAopProxy() {
        if (advisedSupport.isProxyTargetClass()) {
            return new CglibAopProxy(advisedSupport);
        }
        return new JdkDynamicAopProxy(advisedSupport);
    }
}
