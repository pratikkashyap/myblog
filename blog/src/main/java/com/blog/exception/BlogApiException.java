package com.blog.exception;

import org.springframework.http.HttpStatus;

public class BlogApiException extends RuntimeException{

    private HttpStatus status;
    private String message;

    public BlogApiException(HttpStatus status,String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

}
