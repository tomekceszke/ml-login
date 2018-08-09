package com.ceszke.security.mllogin.validator.password;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.ceszke.security.mlloging.path.Paths.LoginValidatorPaths.PasswordTypingTimeValidatorPaths.typingTimeParam;
import static com.ceszke.security.mlloging.path.Paths.LoginValidatorPaths.PasswordTypingTimeValidatorPaths.passwordTypingTimeValidator;

@AllArgsConstructor
@RestController("/"+ passwordTypingTimeValidator)
public class PasswordTypingTimeValidatorController {

    private PasswordTypingTimeValidatorService passwordTypingTimeValidatorService;

    @GetMapping
    public boolean isPasswordValid(@RequestParam(typingTimeParam) int typingTime) {
        return passwordTypingTimeValidatorService.isPasswordValid(typingTime);
    }
    

}
