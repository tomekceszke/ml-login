package com.ceszke.security.mllogin.typingtime;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class TypingTimeService {

    private TypingTimeRepository typingTimeRepository;

    public void collect(int typingTime) {
        if (typingTime > 0) {
            typingTimeRepository.save(TypingTime.builder().typingTime(typingTime).build());
        } else {
            throw new IllegalArgumentException("Typing time cannot be negative or zero");
        }
    }
}
