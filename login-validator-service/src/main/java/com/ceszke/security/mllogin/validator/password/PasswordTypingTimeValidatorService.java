package com.ceszke.security.mllogin.validator.password;

import org.springframework.stereotype.Service;

@Service
public class PasswordTypingTimeValidatorService {

    public boolean isPasswordValid(int typingTime) {

        if(typingTime>1000 && typingTime < 10000)
            return true;
        else
            return false;
    }
}
