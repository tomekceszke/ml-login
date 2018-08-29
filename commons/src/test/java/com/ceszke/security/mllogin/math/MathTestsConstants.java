package com.ceszke.security.mllogin.math;

public interface MathTestsConstants {

    int[] X = {100, 500, 1800, 1900, 2000, 2200, 2400, 2500, 4000, 5000};
    boolean[] Y = {true, true, false, false, false, false, false, false, true, true};

    // computed/learned values
    int MU = 2240;
    int SIGMA_2 = 1878400;
    double EPSILON = 0.00013003d;
}
