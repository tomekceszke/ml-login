package com.ceszke.security.mllogin.path;

public interface Paths {

    String TYPING_TIME_PARAM = "typingTime";

    interface CollectorPaths {
        interface TypingTimePaths {

        }
    }

    interface LoginValidatorPaths {
        interface PasswordTypingTimeValidatorPaths {
            String passwordTypingTimeValidator = "password-typing-time-validator";
            String typingTimeParam = "typingTime";
        }
    }

}
