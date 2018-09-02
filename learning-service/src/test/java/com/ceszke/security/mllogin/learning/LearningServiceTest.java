package com.ceszke.security.mllogin.learning;

import com.ceszke.security.mllogin.client.CollectorClient;
import com.ceszke.security.mllogin.dto.LearnedModelDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LearningServiceTest {

    @Autowired
    LearningService learningService;

    @Autowired
    LearningRepository learningRepository;

    @MockBean
    CollectorClient collectorClient;

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
    public void shouldReturnNullIfNoLearnedModel() {
        // when
        LearnedModelDto learnedModel = learningService.getLearnedModel();
        // then
        assertNull(learnedModel);
    }

    private LearnedModel buildLearnedModel() {
        return LearnedModel.builder().mu(1).sigma2(2).epsilon(3).build();
    }

    @Test
    public void shouldLearnAndSaveModel() throws Exception {
        // given
        when(collectorClient.getSamples()).thenReturn(Stream.of(2000, 2000, 3000, 4000, 2500, 3900, 3700, 3900, 4000, 4003, 2800, 3100, 3700, 3450, 4332).collect(Collectors.toList()));

        // when
        learningService.learn();

        // then
        LearnedModel learnedModel = learningRepository.findAll().stream().reduce((first, second) -> second).orElseThrow(Exception::new);
        assertNotNull(learnedModel);
        assertEquals(3014, learnedModel.getMu());
        assertEquals(649796, learnedModel.getSigma2());
        assertEquals(0.000066925077d, learnedModel.getEpsilon(), 0.0000000001d);
    }


}