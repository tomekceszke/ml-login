package com.ceszke.security.mllogin.math.threshold;

import com.ceszke.security.mllogin.math.gaussian.GaussianUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Map;
import java.util.stream.Collectors;

import static com.ceszke.security.mllogin.math.MathTestsConstants.*;
import static org.junit.Assert.assertEquals;

@Slf4j
public class ThresholdUtilsTest {

    @Test
    public void selectEpsilon() {
        // given
        Map<Double, Boolean> probabilityData = XY.entrySet().stream()
                .collect(Collectors.toMap(entry -> GaussianUtils.getProbability(GaussianUtils.getGaussianDistribution(X), entry.getKey()), Map.Entry::getValue, (p1, p2) -> p1));
        // when
        double epsilon = ThresholdUtils.selectEpsilon(probabilityData);
        // then
        assertEquals(EPSILON, epsilon, EPSILON_DELTA);
    }                        
}