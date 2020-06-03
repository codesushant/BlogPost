package com.learn.raj;

import lombok.NoArgsConstructor;

import java.security.Principal;


@NoArgsConstructor
public class UserAuth implements Principal {
    private String userName;
    private String password;

    public UserAuth(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public String getName() {
        return userName;
    }
}