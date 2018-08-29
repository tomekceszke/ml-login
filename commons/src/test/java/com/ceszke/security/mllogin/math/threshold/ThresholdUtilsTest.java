package com.ceszke.security.mllogin.math.threshold;

import com.ceszke.security.mllogin.math.gaussian.GaussianUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static com.ceszke.security.mllogin.math.MathTestsConstants.*;
import static org.junit.Assert.assertEquals;

@Slf4j
public class ThresholdUtilsTest {

    @Test
    public void selectEpsilon() {
        // given
        double[] p = GaussianUtils.getProbability(X);
        // when
        double epsilon = ThresholdUtils.selectEpsilon(p, Y);
        // then
        assertEquals(EPSILON, epsilon, 0.00000001d);
    }
}