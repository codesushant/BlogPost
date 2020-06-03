package com.learn.raj;

import lombok.Builder;

public class BlogException extends RuntimeException{
    private ResponseCode responseCode;

    public BlogException() {
        super();
        this.responseCode = null;
    }

    @Builder
    public BlogException(ResponseCode responseCode, String message, Throwable cause) {
        super(message, cause);
        this.responseCode = responseCode;
    }

    public static BlogException create(String message, ResponseCode responseCode){
        return BlogException.builder()
                .message(message)
                .responseCode(responseCode)
                .build();
    }
}
