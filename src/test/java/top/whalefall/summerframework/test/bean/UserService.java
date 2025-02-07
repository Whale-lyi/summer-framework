package top.whalefall.summerframework.test.bean;

import lombok.Data;
import top.whalefall.summerframework.beans.BeansException;
import top.whalefall.summerframework.beans.factory.DisposableBean;
import top.whalefall.summerframework.beans.factory.InitializingBean;

@Data
public class UserService implements IUserService, InitializingBean, DisposableBean {

    private String uId;

    private String company;

    private String location;

    private IUserDao userDao;

    @Override
    public String queryUserInfo() {
        return userDao.queryUserName(uId)+", 公司:"+company+", 地点:"+location;
    }

    @Override
    public String register(String userName) {
        return "注册用户：" + userName + " success！";
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("执行：UserService.destroy");
    }

    @Override
    public void afterPropertiesSet() throws BeansException {
        System.out.println("执行：UserService.afterPropertiesSet");
    }

}
