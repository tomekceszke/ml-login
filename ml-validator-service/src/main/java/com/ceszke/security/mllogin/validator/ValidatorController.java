package com.ceszke.security.mllogin.validator;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ValidatorController {

    private ValidatorService validatorService;

    @PostMapping("/")
    public boolean validate(@RequestBody int speed) {
        return validatorService.validate(speed);
    }
}
