package top.whalefall.summerframework.test.bean;

import top.whalefall.summerframework.beans.BeansException;
import top.whalefall.summerframework.beans.factory.DisposableBean;
import top.whalefall.summerframework.beans.factory.InitializingBean;
import top.whalefall.summerframework.beans.factory.annotation.Autowired;
import top.whalefall.summerframework.beans.factory.annotation.Value;
import top.whalefall.summerframework.stereotype.Component;

@Component
public class UserService implements IUserService, InitializingBean, DisposableBean {

    private String uId;

    @Value("${company}")
    private String company;

    @Value("${location}")
    private String location;

    @Autowired
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

    public IUserDao getUserDao() {
        return userDao;
    }

    public String getCompany() {
        return company;
    }

    public String getLocation() {
        return location;
    }

    public String getuId() {
        return uId;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public String toString() {
        return "UserService{" +
                "uId='" + uId + '\'' +
                ", company='" + company + '\'' +
                ", location='" + location + '\'' +
                ", userDao=" + userDao +
                '}';
    }
}
