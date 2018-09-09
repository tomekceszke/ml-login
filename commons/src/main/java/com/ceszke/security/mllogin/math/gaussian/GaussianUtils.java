package com.ceszke.security.mllogin.math.gaussian;

import com.ceszke.security.mllogin.dto.GaussianDistribution;

import java.util.List;

import static com.ceszke.security.mllogin.math.MathUtils.mean;
import static com.ceszke.security.mllogin.math.MathUtils.variance;

public class GaussianUtils {

    public static double[] getProbability(GaussianDistribution gaussianDistribution, List<Integer> x) {
        return getProbability(gaussianDistribution.getMu(), gaussianDistribution.getSigma2(), x.stream().mapToInt(i -> i).toArray());
    }

    public static double[] getProbability(int mu, int sigma2, int... x) {
        double[] p = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            double factor1 = 1 / Math.sqrt(2 * Math.PI * sigma2);
            double factor2 = Math.exp(-1 * Math.pow(x[i] - mu, 2) / (2 * sigma2));
            p[i] = factor1 * factor2;
        }
        return p;
    }

    public static double getProbability(int mu, int sigma2, int x) {
        return getProbability(mu, sigma2, new int[]{x})[0];
    }

    public static double getProbability(GaussianDistribution gaussianDistribution, int x) {
        return getProbability(gaussianDistribution.getMu(), gaussianDistribution.getSigma2(), x);
    }

    public static double[] getProbability(GaussianDistribution gaussianDistribution, int... x) {
        return getProbability(gaussianDistribution.getMu(), gaussianDistribution.getSigma2(), x);
    }

    public static GaussianDistribution getGaussianDistribution(List<Integer> x) {
        return getGaussianDistribution(x.stream().mapToInt(i -> i).toArray());
    }

    private static GaussianDistribution getGaussianDistribution(int[] x) {
        int mu = mean(x);
        int sigma2 = variance(x, mu);
        return GaussianDistribution.builder().mu(mu).sigma2(sigma2).build();
    }

}
