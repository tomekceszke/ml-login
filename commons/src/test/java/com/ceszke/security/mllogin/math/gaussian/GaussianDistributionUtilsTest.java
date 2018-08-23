package com.ceszke.security.mllogin.math.gaussian;

import com.ceszke.security.mllogin.dto.GaussianDistribution;
import org.junit.Test;

import static com.ceszke.security.mllogin.math.gaussian.GaussianDistributionUtils.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// @Slf4j

/**
 * Function to compute the Gaussian pdf (probability density function)
 */
public class GaussianDistributionUtilsTest {

    @Test
    public void getProbabilityTest() {
        GaussianDistribution learntGaussianDistribution = getGaussianDistribution(new int[]{0, 1, 2, 2, 3, 3, -4, 2});
        assertTrue(getProbability(learntGaussianDistribution, 100) < getProbability(learntGaussianDistribution, 5));
        assertTrue(getProbability(learntGaussianDistribution, -6) > getProbability(learntGaussianDistribution, 50));
    }

    @Test
    public void getGaussianDistributionTest() {
        GaussianDistribution gaussianDistribution = getGaussianDistribution(new int[]{1000, 2000, 2000, 3000});
        // log.info(gaussianDistribution.toString());
    }

    @Test
    public void meanTest() {
        assertEquals(2, mean(new int[]{1, 2, 3}));
        assertEquals(20, mean(new int[]{5, 10, 15, 20, 50}));
    }

    @Test
    public void varianceTest() {
        int[] data = new int[]{4, -3, 2};
        assertEquals(26 / 3, variance(data, mean(data)));
    }
}