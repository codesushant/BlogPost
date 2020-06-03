package com.learn.raj;

public enum ResponseCode {

    REQUEST_EMPTY(400),
    INCORRECT_USER(401);

    private int httpCode;

    private ResponseCode(int httpCode) {
        this.httpCode = httpCode;
    }

    private int getHttpCode(){
        return httpCode;
    }
}