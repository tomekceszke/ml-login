package com.ceszke.security.mllogin.validator;

import com.ceszke.security.mllogin.client.CollectorClient;
import com.ceszke.security.mllogin.client.LearningClient;
import com.ceszke.security.mllogin.dto.LearnedModelDto;
import com.ceszke.security.mllogin.math.gaussian.GaussianDistributionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ValidatorService {

    @SuppressWarnings("unused")
    private CollectorClient collectorClient;

    @SuppressWarnings("unused")
    private LearningClient learningClient;


    public boolean validate(int speed) {
        if (collectorClient.isReadyToDetect()) {
            LearnedModelDto learnedModelDto = learningClient.getLearnedModel();
            double probability = GaussianDistributionUtils.getProbability(learnedModelDto.getMu(), learnedModelDto.getSigma2(), speed);
            return (probability > learnedModelDto.getEpsilon());
        }  else {
            log.warn("Not ready to predict");
            collectorClient.collect(speed);
            return true;
        }
    }

}
