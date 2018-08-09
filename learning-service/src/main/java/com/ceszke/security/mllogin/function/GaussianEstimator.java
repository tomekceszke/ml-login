package com.ceszke.security.mllogin.function;

import org.springframework.stereotype.Component;

@Component
public class GaussianEstimator {

    public GaussianParams estimate(int[] data) {

        int mu = CommonFunctions.mean(data);
        int sigma2 = CommonFunctions.variance(data, mu);

        return GaussianParams.builder().mu(mu).sigma2(sigma2).build();
    }
}
