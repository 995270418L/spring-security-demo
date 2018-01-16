package com.imooc.utils;


import org.springframework.security.core.AuthenticationException;

public class ValidateException extends AuthenticationException {

    public ValidateException(String explanation) {
        super(explanation);
    }
}
