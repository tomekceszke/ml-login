package com.ceszke.security.mllogin.math.threshold;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ThresholdUtils {

    public static double selectEpsilon(double[] p, boolean[] y) {
        double bestEpsilon = 0;
        double bestF1 = 0;
        double minValidation = Arrays.stream(p).min().getAsDouble();
        double maxValidation = Arrays.stream(p).max().getAsDouble();
        double step = (maxValidation - minValidation) / 1000;

        for (double epsilon = minValidation; epsilon < maxValidation; epsilon += step) {
            List<Boolean> predictions = getPredictions(p, epsilon);
            double sumFN = getSum(i -> !predictions.get(i) && y[i], predictions.size());
            double sumFP = getSum(i -> predictions.get(i) && !y[i], predictions.size());
            double sumTP = getSum(i -> predictions.get(i) && y[i], predictions.size());
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

    private static List<Boolean> getPredictions(double[] p, double epsilon) {
        return Arrays.stream(p).mapToObj(validationSample -> validationSample < epsilon).collect(Collectors.toList());
    }
}
