package com.ceszke.security.mllogin.math.gaussian;

import com.ceszke.security.mllogin.dto.GaussianDistribution;
import org.junit.Test;

import static com.ceszke.security.mllogin.math.MathTestsConstants.MU;
import static com.ceszke.security.mllogin.math.MathTestsConstants.SIGMA_2;
import static com.ceszke.security.mllogin.math.MathTestsConstants.X;
import static com.ceszke.security.mllogin.math.gaussian.GaussianUtils.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class GaussianUtilsTest {

    @Test
    public void getProbabilityTest() {
        GaussianDistribution learnedGaussianDistribution = getGaussianDistribution(X);
        assertTrue(getProbability(learnedGaussianDistribution, 2050) > getProbability(learnedGaussianDistribution, 5000));
        assertTrue(getProbability(learnedGaussianDistribution, 1000) < getProbability(learnedGaussianDistribution, 2800));
    }

    @Test
    public void getGaussianDistributionTest() {
        GaussianDistribution gaussianDistribution = getGaussianDistribution(X);
        assertEquals(MU, gaussianDistribution.getMu());
        assertEquals(SIGMA_2,gaussianDistribution.getSigma2());
    }
}