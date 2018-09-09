package com.ceszke.security.mllogin.math.gaussian;

import com.ceszke.security.mllogin.dto.GaussianDistribution;
import org.junit.Test;

import static com.ceszke.security.mllogin.math.MathTestsConstants.*;
import static com.ceszke.security.mllogin.math.gaussian.GaussianUtils.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class GaussianUtilsTest {

    @Test
    public void getProbabilityTest() {
        GaussianDistribution learnedGaussianDistribution = getGaussianDistribution(X);
        assertTrue(getProbability(learnedGaussianDistribution, X_MIN) < getProbability(learnedGaussianDistribution, X_MIN + (X_MIN / 2)));
        assertTrue(getProbability(learnedGaussianDistribution, X_MAX) > getProbability(learnedGaussianDistribution, X_MAX - (X_MAX / 2)));
    }

    @Test
    public void getGaussianDistributionTest() {
        GaussianDistribution gaussianDistribution = getGaussianDistribution(X);
        assertEquals(MU, gaussianDistribution.getMu());
        assertEquals(SIGMA2, gaussianDistribution.getSigma2());
    }
}