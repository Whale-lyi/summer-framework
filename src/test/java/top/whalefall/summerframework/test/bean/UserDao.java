package top.whalefall.summerframework.test.bean;

import top.whalefall.summerframework.beans.BeansException;
import top.whalefall.summerframework.beans.factory.DisposableBean;
import top.whalefall.summerframework.beans.factory.InitializingBean;

import java.util.HashMap;
import java.util.Map;

public class UserDao implements IUserDao {

    private static final Map<String, String> hashMap = new HashMap<>();

    public void initDataMethod(){
        System.out.println("执行：UserDao.init-method");
        hashMap.put("10001", "小傅哥");
        hashMap.put("10002", "八杯水");
        hashMap.put("10003", "阿毛");
    }

    public void destroyDataMethod(){
        System.out.println("执行：UserDao.destroy-method");
        hashMap.clear();
    }

    @Override
    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }

}

