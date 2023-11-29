package top.whalefall.summerframework.context.support;

import lombok.Getter;
import lombok.NoArgsConstructor;
import top.whalefall.summerframework.beans.BeansException;

@Getter
@NoArgsConstructor
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

    private String[] configLocations;

    /**
     * 从 XML 中加载 BeanDefinition，并刷新上下文.
     *
     * @param configLocations
     * @throws BeansException
     */
    public ClassPathXmlApplicationContext(String[] configLocations) throws BeansException {
        this.configLocations = configLocations;
        refresh();
    }

    public ClassPathXmlApplicationContext(String configLocation) throws BeansException {
        this(new String[]{configLocation});
    }

    @Override
    protected String[] getConfigLocations() {
        return configLocations;
    }
}
