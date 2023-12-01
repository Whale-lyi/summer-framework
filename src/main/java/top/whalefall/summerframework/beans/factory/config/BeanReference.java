package top.whalefall.summerframework.beans.factory.config;

/**
 * Bean引用
 */
public class BeanReference {
    private final String beanName;

    public String getBeanName() {
        return beanName;
    }

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }
}
