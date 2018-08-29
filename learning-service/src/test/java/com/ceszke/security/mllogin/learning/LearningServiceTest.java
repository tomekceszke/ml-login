package com.ceszke.security.mllogin.learning;

import com.ceszke.security.mllogin.dto.LearnedModelDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LearningServiceTest {

    @Autowired
    LearningService learningService;

    @Autowired
    LearningRepository learningRepository;

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
}