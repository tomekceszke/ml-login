package com.ceszke.security.mllogin.validator;

import com.ceszke.security.mllogin.client.CollectorClient;
import com.ceszke.security.mllogin.client.LearningClient;
import com.ceszke.security.mllogin.dto.LearnedModelDto;
import com.ceszke.security.mllogin.math.gaussian.GaussianUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class ValidatorService {

    private CollectorClient collectorClient;

    private LearningClient learningClient;


    public boolean validate(int speed) {
        LearnedModelDto learnedModelDto = learningClient.getLearnedModel();
        if (learnedModelDto == null) {
            if (collectorClient.isReadyToLearn()) {
                learningClient.learn();
                learnedModelDto = learningClient.getLearnedModel();
            } else {
                log.warn("Not ready to predict");
                collectorClient.collect(speed);
                return true;
            }
        }
        double probability = GaussianUtils.getProbability(learnedModelDto.getMu(), learnedModelDto.getSigma2(), speed);
        return (probability > learnedModelDto.getEpsilon());
    }


}
