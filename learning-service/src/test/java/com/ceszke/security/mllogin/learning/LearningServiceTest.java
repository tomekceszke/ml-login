package com.ceszke.security.mllogin.learning;

import com.ceszke.security.mllogin.dto.LearnedModelDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.ceszke.security.mllogin.math.MathTestsConstants.X;
import static com.ceszke.security.mllogin.math.MathUtils.mean;
import static com.ceszke.security.mllogin.math.MathUtils.variance;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
//@DirtiesContext

public class LearningServiceTest {

    @Autowired
    private LearningService learningService;

    @Autowired
    private LearningRepository learningRepository;

    private final static String TEST_SESSION_ID = "1";

    @After
    public void tearDown() throws Exception {
        learningRepository.deleteAll();
    }

    @Test
    public void shouldReturnLearnedModel() {
        // when
        LearnedModel learnedModel = buildLearnedModel();
        learningRepository.save(learnedModel);
        // when
        LearnedModelDto learnedModelDto = learningService.getLearnedModel(TEST_SESSION_ID);
        // then
        assertEquals(new ModelMapper().map(learnedModel, LearnedModelDto.class), learnedModelDto);
    }

    @Test
    public void shouldReturnLastLearnedModelIfMoreAvailable() {
        // when
        learningRepository.save(buildLearnedModel());
        LearnedModel learnedModel1 = buildLearnedModel();
        learnedModel1.setEpsilon(4);
        learningRepository.save(learnedModel1);
        // when
        LearnedModelDto learnedModelDto = learningService.getLearnedModel(TEST_SESSION_ID);
        // then
        assertEquals(new ModelMapper().map(learnedModel1, LearnedModelDto.class), learnedModelDto);
    }

    @Test
    public void shouldReturnNullIfNoLearnedModel2ndCheck() {
        // when
        LearnedModelDto learnedModel = learningService.getLearnedModel(TEST_SESSION_ID);
        // then
        assertNull(learnedModel);
    }

    @Test
    public void shouldLearnAndSaveModel() throws Exception {
        // when
        learningService.learn(X, TEST_SESSION_ID);
        // then
        LearnedModel learnedModel = learningRepository.findAll().stream().reduce((first, second) -> second).orElseThrow(Exception::new);
        assertNotNull(learnedModel);
        int[] x = X.stream().mapToInt(i -> i).toArray();
        int mean = mean(x);
        assertEquals(mean, learnedModel.getMu(), 500);
        assertEquals(variance(x, mean), learnedModel.getSigma2(), 50000);
    }
    
    @Test
    public void shouldReturnNullIfNoLearnedModel() {
        // when
        LearnedModelDto learnedModel = learningService.getLearnedModel(TEST_SESSION_ID);
        // then
        assertNull(learnedModel);
    }

    private LearnedModel buildLearnedModel() {
        return LearnedModel.builder().sessionId(TEST_SESSION_ID).mu(1).sigma2(2).epsilon(3).build();
    }

}