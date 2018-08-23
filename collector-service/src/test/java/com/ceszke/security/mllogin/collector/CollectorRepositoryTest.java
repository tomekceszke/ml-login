package com.ceszke.security.mllogin.collector;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.ceszke.security.mllogin.collector.CollectorTestUtils.buildRandomSamples;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CollectorRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CollectorRepository collectorRepository;


    @Test
    public void findAll() {
        // given
        final int numberOfSamples = 5;
        buildRandomSamples(numberOfSamples).forEach(entityManager::persist);
        entityManager.flush();
        // when
        List<Sample> typingTimes = collectorRepository.findAll();
        // then
        assertEquals(numberOfSamples, typingTimes.size());
    }

}