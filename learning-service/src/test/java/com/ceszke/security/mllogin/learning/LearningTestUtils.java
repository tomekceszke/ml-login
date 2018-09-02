package com.ceszke.security.mllogin.learning;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LearningTestUtils {
    public static List<Integer> getRandomSamples() {
        Random random = new Random();
        return IntStream.range(0,100).boxed().map(i-> random.nextInt(1000) + 2000).collect(Collectors.toList());
    }
}
