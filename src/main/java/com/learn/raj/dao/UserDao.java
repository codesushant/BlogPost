package com.learn.raj.dao;

import com.learn.raj.entities.User;
import com.learn.raj.requests.UserRegisterRequest;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDao extends AbstractDAO<User> {
    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    public UserDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public boolean storeUser(UserRegisterRequest userRegisterRequest){
        User user = new User(userRegisterRequest.getUsername(), userRegisterRequest.getPassword());
        persist(user);
        return true;
    }

    public List<User> fetchAll(){
        return list(namedQuery("com.learn.java.Users.findAll"));
    }

    public User getUser(long id){
        return get(id);
    }

//    public User getUser(String username){
//        return (User) namedQuery("com.learn.java.Users.findUserByUsername");
//    }

    public List<User> findByName(String name) {
//        StringBuilder builder = new StringBuilder("%");
//        builder.append(name).append("%");
        return list(
                namedQuery("com.learn.java.Users.findUserByUsername")
                        .setParameter("user_name", name/*builder.toString()*/)
        );
    }
}
