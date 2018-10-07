package com.ceszke.security.mllogin.collector;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CollectorTestUtils {

    public static final String TEST_SESSION_ID = "1";

    static List<Sample> buildRandomSamples(int numberOfSamples) {
        return IntStream.range(0, numberOfSamples).mapToObj(i -> buildRandomSample()).collect(Collectors.toList());
    }

    private static Sample buildRandomSample() {
        Random random = new Random();
        int randomSpeed = random.nextInt(1000) + 2000;
        return Sample.builder().sessionId(TEST_SESSION_ID).speed(randomSpeed).build();
    }

}