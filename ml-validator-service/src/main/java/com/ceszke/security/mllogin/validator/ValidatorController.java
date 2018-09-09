package com.ceszke.security.mllogin.validator;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor
public class ValidatorController {

    private ValidatorService validatorService;

    @GetMapping("/{speed}")
    @ResponseStatus(OK)
    public boolean validate(@PathVariable int speed) {
        return validatorService.validate(speed);
    }
}
