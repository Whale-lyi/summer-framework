package top.whalefall.summerframework.test.event;


import top.whalefall.summerframework.context.ApplicationListener;
import top.whalefall.summerframework.context.event.ContextRefreshedEvent;

public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("刷新事件: " + this.getClass().getName());
    }

}
