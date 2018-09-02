package com.ceszke.security.mllogin.math.threshold;

import java.util.*;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ThresholdUtils {

    public static double selectEpsilon(Map<Double, Boolean> trainingData) {
        double bestEpsilon = 0;
        double bestF1 = 0;
        Set<Double> p = trainingData.keySet();
        List<Boolean> y = new ArrayList<>(trainingData.values());
        double minValidation = p.stream().min(Double::compareTo).get();
        double maxValidation = p.stream().max(Double::compareTo).get();
        double step = (maxValidation - minValidation) / 1000;

        for (double epsilon = minValidation; epsilon < maxValidation; epsilon += step) {
            List<Boolean> predictions = getPredictions(p, epsilon);
            double sumFN = getSum(i -> !predictions.get(i) && y.get(i), predictions.size());
            double sumFP = getSum(i -> predictions.get(i) && !y.get(i), predictions.size());
            double sumTP = getSum(i -> predictions.get(i) && y.get(i), predictions.size());
            double precision = sumTP / (sumTP + sumFP);
            double recall = sumTP / (sumTP + sumFN);
            double divisor = precision + recall;
            if (divisor == 0) {
                continue;
            }
            double f1 = (2 * precision * recall) / divisor;
            if (f1 > bestF1) {
                bestF1 = f1;
                bestEpsilon = epsilon;
            }
        }
        return bestEpsilon;
    }

    private static double getSum(IntPredicate intPredicate, int size) {
        return IntStream.range(0, size).filter(intPredicate).count();
    }

    private static List<Boolean> getPredictions(Set<Double> p, double epsilon) {
        return p.stream().map(validationSample -> validationSample < epsilon).collect(Collectors.toList());
    }
}
