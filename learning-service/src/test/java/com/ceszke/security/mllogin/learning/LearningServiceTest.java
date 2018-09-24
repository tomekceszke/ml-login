package com.ceszke.security.mllogin.learning;

import com.ceszke.security.mllogin.client.CollectorClient;
import com.ceszke.security.mllogin.dto.LearnedModelDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static com.ceszke.security.mllogin.math.MathTestsConstants.X;
import static com.ceszke.security.mllogin.math.MathUtils.mean;
import static com.ceszke.security.mllogin.math.MathUtils.variance;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext

public class LearningServiceTest {

    @Autowired
    private LearningService learningService;

    @Autowired
    private LearningRepository learningRepository;

    @MockBean
    private CollectorClient collectorClient;

    @Test
    public void shouldReturnLearnedModel() {
        // when
        LearnedModel learnedModel = buildLearnedModel();
        learningRepository.save(learnedModel);
        // when
        LearnedModelDto learnedModelDto = learningService.getLearnedModel();
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
        LearnedModelDto learnedModelDto = learningService.getLearnedModel();
        // then
        assertEquals(new ModelMapper().map(learnedModel1, LearnedModelDto.class), learnedModelDto);
    }

    @Test
    public void shouldReturnNullIfNoLearnedModel2ndCheck() {
        // when
        LearnedModelDto learnedModel = learningService.getLearnedModel();
        // then
        assertNull(learnedModel);
    }

    @Test
    public void shouldLearnAndSaveModel() throws Exception {
        // given
        when(collectorClient.getSamples()).thenReturn(X);
        // when
        learningService.learn();
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
        LearnedModelDto learnedModel = learningService.getLearnedModel();
        // then
        assertNull(learnedModel);
    }

    private LearnedModel buildLearnedModel() {
        return LearnedModel.builder().mu(1).sigma2(2).epsilon(3).build();
    }
}