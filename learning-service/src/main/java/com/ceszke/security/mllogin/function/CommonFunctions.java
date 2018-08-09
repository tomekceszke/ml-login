package com.ceszke.security.mllogin.function;

public class CommonFunctions {

    public static int mean(int[] data) {
        int sum = 0;
        for (int item : data) {
            sum += item;
        }
        return sum / data.length;
    }

    public static int variance(int[] data, int mu) {
        int sum = 0;
        for (int item : data) {
            sum += Math.pow(item - mu, 2);
        }
        return sum / data.length;
    }
}
