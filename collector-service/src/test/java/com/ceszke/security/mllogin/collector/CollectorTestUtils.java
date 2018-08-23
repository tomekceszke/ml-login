package com.ceszke.security.mllogin.collector;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class CollectorTestUtils {

    static Set<Sample> buildRandomSamples(int numberOfSamples) {
        Set<Sample> samples = new HashSet<>();
        for (int i = 0; i < numberOfSamples; i++) {
            samples.add(buildRandomSample());
        }
        return samples;
    }

    static Sample buildRandomSample() {
        Random random = new Random();
        int randomSpeed = random.nextInt(1000) + 2000;
        return Sample.builder().speed(randomSpeed).build();
    }

}