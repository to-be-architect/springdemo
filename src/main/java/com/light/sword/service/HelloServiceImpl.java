package com.light.sword.service;

import com.light.sword.dao.HelloDao;
import com.light.sword.dao.HelloDaoImpl;

/**
 * @author: Jack
 * 2019-11-25 23:09
 */
public class HelloServiceImpl implements HelloService {
    // 依赖注入的思想,就是把这句代码放到容器中单独管理,而不是写死在代码里.
    // private HelloDao helloDao = new HelloDaoImpl();
    private HelloDao helloDao;

    public HelloDao getHelloDao() {
        return helloDao;
    }

    public void setHelloDao(HelloDao helloDao) {
        this.helloDao = helloDao;
    }

    @Override
    public String service1(String name) {
        return helloDao.say(name);
    }

    @Override
    public String service2(String name) {
        return helloDao.say("$" + name);
    }

}
