package com.ceszke.security.mllogin.security;

import org.springframework.security.core.AuthenticationException;


public class MLAuthenticationException extends AuthenticationException {

    public MLAuthenticationException(String msg) {
        super(msg);
    }

}
