package top.whalefall.summerframework.test.common.event;


import top.whalefall.summerframework.context.ApplicationListener;
import top.whalefall.summerframework.context.event.ContextClosedEvent;

public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("关闭事件: " + this.getClass().getName());
    }

}
