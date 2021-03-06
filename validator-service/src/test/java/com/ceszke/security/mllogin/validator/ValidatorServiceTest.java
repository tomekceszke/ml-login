package com.ceszke.security.mllogin.validator;

import com.ceszke.security.mllogin.client.CollectorClient;
import com.ceszke.security.mllogin.client.LearningClient;
import com.ceszke.security.mllogin.dto.LearnedModelDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static com.ceszke.security.mllogin.math.MathTestsConstants.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
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

    private static final String TEST_SESSION_ID = "1";

    @Test
    public void shouldInvalidateIfSpeedIsToFast() {
        // given
        when(collectorClient.isReadyToLearn(anyString())).thenReturn(true);
        when(learningClient.getLearnedModel(anyString())).thenReturn(LearnedModelDto.builder().epsilon(EPSILON).mu(MU).sigma2(SIGMA2).build());
        // when/then
        assertFalse(validatorService.validate(X_MIN / 2, TEST_SESSION_ID));
        assertFalse(validatorService.validate(X_MIN / 3, TEST_SESSION_ID));
        assertFalse(validatorService.validate(X_MIN / 4, TEST_SESSION_ID));
    }

    @Test
    public void shouldInvalidateIfSpeedIsToSlow() {
        // given
        when(collectorClient.isReadyToLearn(anyString())).thenReturn(true);
        when(learningClient.getLearnedModel(anyString())).thenReturn(LearnedModelDto.builder().epsilon(EPSILON).mu(MU).sigma2(SIGMA2).build());
        // when/then
        assertFalse(validatorService.validate(X_MAX * 2, TEST_SESSION_ID));
        assertFalse(validatorService.validate(X_MAX * 3, TEST_SESSION_ID));
        assertFalse(validatorService.validate(X_MAX * 4, TEST_SESSION_ID));
    }

    @Test
    public void shouldValidateIfSpeedIsInLearnedRange() {
        // given
        when(collectorClient.isReadyToLearn(anyString())).thenReturn(true);
        when(learningClient.getLearnedModel(anyString())).thenReturn(LearnedModelDto.builder().epsilon(EPSILON).mu(MU).sigma2(SIGMA2).build());
        // when/then
        X.forEach(i -> assertTrue(validatorService.validate(i, TEST_SESSION_ID)));
    }

    @Test
    public void shouldValidateIfSpeedIsCloseToLearnedRange() {
        // given
        when(collectorClient.isReadyToLearn(anyString())).thenReturn(true);
        when(learningClient.getLearnedModel(anyString())).thenReturn(LearnedModelDto.builder().epsilon(EPSILON).mu(MU).sigma2(SIGMA2).build());
        // when/then
        assertTrue(validatorService.validate(X_MIN - (X_MIN / 4), TEST_SESSION_ID));
        assertTrue(validatorService.validate(X_MAX + (X_MAX / 4), TEST_SESSION_ID));
    }
}