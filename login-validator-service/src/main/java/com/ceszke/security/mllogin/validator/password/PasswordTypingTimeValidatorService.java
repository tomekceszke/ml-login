package com.ceszke.security.mllogin.validator.password;

import com.ceszke.security.mllogin.math.gaussian.GaussianDistribution;
import org.springframework.stereotype.Service;

import static com.ceszke.security.mllogin.math.gaussian.GaussianDistributionUtils.getProbability;

@Service
public class PasswordTypingTimeValidatorService {

    private final static double EPSILON = 0.05;

    public boolean isPasswordValid(int typingTime) {
        GaussianDistribution learntGaussianDistributionParams = null; // TODO
        double probability = getProbability(learntGaussianDistributionParams, typingTime);

        return probability >= EPSILON;
    }
}
