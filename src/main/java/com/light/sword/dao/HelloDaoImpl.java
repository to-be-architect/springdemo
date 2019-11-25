package com.light.sword.dao;

import java.util.Date;

/**
 * @author: Jack
 * 2019-11-25 23:08
 */
public class HelloDaoImpl implements HelloDao {

    @Override
    public String say(String name) {
        return "Hello," + name + ", now is:" + new Date();
    }
}
