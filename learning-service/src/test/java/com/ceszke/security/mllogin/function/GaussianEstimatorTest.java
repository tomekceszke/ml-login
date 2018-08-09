package com.ceszke.security.mllogin.function;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class GaussianEstimatorTest {

    @Test
    public void estimate() {
        GaussianParams gaussianParams = new GaussianEstimator().estimate(new int[]{1000, 2000, 2000, 3000});
        log.info(gaussianParams.toString());
    }
}