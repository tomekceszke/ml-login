package com.ceszke.security.mllogin.math.gaussian;

import com.ceszke.security.mllogin.dto.GaussianDistribution;

public class GaussianDistributionUtils {

    public static double[] getProbability(int mu, int sigma2, int...data) {
        double[] p = new double[data.length];
        for (int i = 0; i < data.length; i++) {
            double factor1 = 1 / Math.sqrt(2 * Math.PI * sigma2);
            double factor2 = Math.exp(-1*Math.pow(data[i] - mu, 2) / (2 * sigma2));
            p[i] = factor1 * factor2;
        }
        return p;
    }

    public static double getProbability(GaussianDistribution gaussianDistribution, int data) {
        return getProbability(gaussianDistribution.getMu(), gaussianDistribution.getSigma2(), data)[0];
    }

    static GaussianDistribution getGaussianDistribution(int[] data) {
        int mu = mean(data);
        int sigma2 = variance(data, mu);
        return GaussianDistribution.builder().mu(mu).sigma2(sigma2).build();
    }

    static int mean(int[] data) {
        int sum = 0;
        for (int item : data) {
            sum += item;
        }
        return sum / data.length;
    }

    static int variance(int[] data, int mu) {
        int sum = 0;
        for (int item : data) {
            sum += Math.pow(item - mu, 2);
        }
        return sum / data.length;
    }
}
