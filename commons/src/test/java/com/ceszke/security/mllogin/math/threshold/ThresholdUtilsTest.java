package com.ceszke.security.mllogin.math.threshold;

import com.ceszke.security.mllogin.dto.GaussianDistribution;
import com.ceszke.security.mllogin.math.gaussian.GaussianUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.ceszke.security.mllogin.math.MathTestsConstants.*;
import static org.junit.Assert.assertEquals;

@Slf4j
public class ThresholdUtilsTest {

    @Test
    public void selectEpsilon() {
        // given
        GaussianDistribution gaussianDistribution = GaussianUtils.getGaussianDistribution(X);
        double[] p = GaussianUtils.getProbability(gaussianDistribution, X);
        // when
        Map<Double, Boolean> data = IntStream.range(0, p.length).boxed().collect(Collectors.toMap(i -> p[i], Y::get));
        double epsilon = ThresholdUtils.selectEpsilon(data);
        // then
        assertEquals(EPSILON, epsilon, 0.00000001d);
    }
}