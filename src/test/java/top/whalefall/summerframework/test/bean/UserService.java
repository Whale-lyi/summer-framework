package top.whalefall.summerframework.test.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.whalefall.summerframework.beans.BeansException;
import top.whalefall.summerframework.beans.factory.DisposableBean;
import top.whalefall.summerframework.beans.factory.InitializingBean;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserService implements InitializingBean, DisposableBean {

    private String uId;
    private String company;
    private String location;
    private UserDao userDao;

    public String queryUserInfo() {
        return userDao.queryUserName(uId)+", 公司:"+company+", 地点:"+location;
    }

    @Override
    public void destroy() throws BeansException {
        System.out.println("执行：UserService.destroy");
    }

    @Override
    public void afterPropertiesSet() throws BeansException {
        System.out.println("执行：UserService.afterPropertiesSet");
    }
}
