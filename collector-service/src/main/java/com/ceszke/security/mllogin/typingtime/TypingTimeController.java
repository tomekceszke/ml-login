package com.ceszke.security.mllogin.typingtime;

import com.ceszke.security.mllogin.path.Paths;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@AllArgsConstructor
public class TypingTimeController {

    private TypingTimeService typingTimeService;

    @PostMapping("/{" + Paths.TYPING_TIME_PARAM + "}")
    @ResponseStatus(HttpStatus.CREATED)
    public void collect(@PathVariable int typingTime) {
            typingTimeService.collect(typingTime);
    }
}

