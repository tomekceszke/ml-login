package com.ceszke.security.mllogin.typingtime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TypingTimeRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TypingTimeRepository typingTimeRepository;

    @Test
    public void findAll() {
        // given
        TypingTime typingTime1 = TypingTime.builder().typingTime(3000).build();
        TypingTime typingTime2 = TypingTime.builder().typingTime(4000).build();
        TypingTime typingTime3 = TypingTime.builder().typingTime(5000).build();
        entityManager.persist(typingTime1);
        entityManager.persist(typingTime2);
        entityManager.persist(typingTime3);
        entityManager.flush();

        // when
        List<TypingTime> typingTimes = typingTimeRepository.findAll();

        // then
        assertEquals(3, typingTimes.size());
        assertEquals(Stream.of(3000, 4000, 5000).collect(Collectors.toList()), typingTimes.stream().map(TypingTime::getTypingTime).collect(Collectors.toList()));

    }

}