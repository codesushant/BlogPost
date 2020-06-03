package com.learn.raj;

import com.learn.raj.dao.UserDao;
import com.learn.raj.entities.User;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.hibernate.UnitOfWork;

import java.util.List;
import java.util.Optional;

public class BlogAuthenticator implements Authenticator<BasicCredentials, User> {

    private UserDao userDao;

    public BlogAuthenticator(UserDao userDao) {
        this.userDao = userDao;
    }

    @UnitOfWork
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
        List<User> users = userDao.fetchAll();

        for(User user: users){
            if(user.getUserName().equals(credentials.getUsername()) && user.getPassword().equals(credentials.getPassword())){
                User user1 = userDao.getUser(user.getUserId());
                return Optional.of(user1);
            }
        }

//        if ("crimson".equals(credentials.getPassword())) {
//            return Optional.of(new User());
//        }
        return Optional.empty();
    }
}