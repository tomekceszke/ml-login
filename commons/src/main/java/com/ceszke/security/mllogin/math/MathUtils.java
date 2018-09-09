package com.ceszke.security.mllogin.math;

public class MathUtils {

    public static int mean(int[] x) {
        int sum = 0;
        for (int item : x) {
            sum += item;
        }
        return sum / x.length;
    }

    public static int variance(int[] x, int mu) {
        int sum = 0;
        for (int item : x) {
            sum += Math.pow(item - mu, 2);
        }
        return sum / x.length;
    }
}
