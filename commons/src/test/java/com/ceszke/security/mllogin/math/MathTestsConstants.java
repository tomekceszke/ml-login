package com.ceszke.security.mllogin.math;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface MathTestsConstants {

    List<Integer> X = Stream.of(100, 500, 1800, 1900, 2000, 2200, 2400, 2500, 4000, 5000).collect(Collectors.toList());
    List<Boolean> Y = Stream.of(true, true, false, false, false, false, false, false, true, true).collect(Collectors.toList());

    // computed/learned values
    int MU = 2240;
    int SIGMA_2 = 1878400;
    double EPSILON = 0.00013003d;
}
