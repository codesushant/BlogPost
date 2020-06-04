package com.learn.raj.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import java.security.Principal;


@Entity
@Table(name="users")

@NamedQueries({
        @NamedQuery(name = "com.learn.java.Users.findAll",
                query = "select u from User u"),
        @NamedQuery(name = "com.learn.java.Users.findUserByUsername",
                query = "select u from User u where u.userName = :user_name"),
})

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Principal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    private String userName;
    private String password;
    private String email;

    public User(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    @Override
    public String getName() {
        return userName;
    }
}
