package top.whalefall.summerframework.test.bean;

public class UserService {
    private String uId;

    private String company;

    private String location;

    private IUserDao userDao;

    public String queryUserInfo() {
        return userDao.queryUserName(uId)+", 公司:"+company+", 地点:"+location;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    public String getuId() {
        return uId;
    }

    public String getCompany() {
        return company;
    }

    public String getLocation() {
        return location;
    }

    public IUserDao getUserDao() {
        return userDao;
    }
}
