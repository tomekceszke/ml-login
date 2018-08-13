package com.ceszke.security.mllogin.validator.password;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Stream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
public class PasswordTypingTimeValidatorServiceTest {

    @Autowired
    PasswordTypingTimeValidatorService passwordTypingTimeValidatorService;

    @Test
    public void shouldValidatePasswordsTypedInCorrectTime() {
        Stream.of(2000, 5000, 9000).forEach(time -> assertTrue(passwordTypingTimeValidatorService.isPasswordValid(time)));
    }

    @Test
    public void shouldInvalidatePasswordsTypedTooFastOrTooSlow() {
        Stream.of(0, 100, 500, 12000, 15000, 20000).forEach(time -> assertFalse(passwordTypingTimeValidatorService.isPasswordValid(time)));
    }
}