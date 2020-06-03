package com.learn.raj;

import com.learn.raj.dao.UserDao;

public class UserAuthenticatorProxy {
    private UserDao userDao;

    public UserAuthenticatorProxy(UserDao userDao) {
        this.userDao = userDao;
    }


}
