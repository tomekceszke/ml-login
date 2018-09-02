package com.ceszke.security.mllogin.validator;

import com.ceszke.security.mllogin.client.CollectorClient;
import com.ceszke.security.mllogin.client.LearningClient;
import com.ceszke.security.mllogin.dto.GaussianDistribution;
import com.ceszke.security.mllogin.dto.LearnedModelDto;
import com.ceszke.security.mllogin.math.gaussian.GaussianUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidatorServiceTest {

    @Autowired
    private ValidatorService validatorService;

    @MockBean
    private CollectorClient collectorClient;

    @MockBean
    private LearningClient learningClient;

    @Test
    @Ignore
    public void validate() {
        // given
        GaussianDistribution gaussianDistribution = GaussianUtils.getGaussianDistribution(Stream.of(1000, 1200, 900).collect(Collectors.toList()));

        // when
        when(collectorClient.isReadyToLearn()).thenReturn(true);
        when(learningClient.getLearnedModel()).thenReturn(LearnedModelDto.builder().epsilon(0.05).mu(gaussianDistribution.getMu()).sigma2(gaussianDistribution.getSigma2()).build());
        boolean result = validatorService.validate(1100);

        // then
        assertTrue(result);
    }
}