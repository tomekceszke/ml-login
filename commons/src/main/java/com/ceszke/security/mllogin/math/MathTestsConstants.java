package com.ceszke.security.mllogin.math;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.ceszke.security.mllogin.math.data.DataUtils.addPositiveSamples;

public interface MathTestsConstants {

    List<Integer> X = Stream.of(4053, 4820, 5193, 4393, 4946, 5880, 5834, 4798, 5813, 4476, 5813, 4287, 4168, 4231, 5766, 4937, 5754, 5806, 4011, 4350, 5840, 5823, 5503, 5668, 4020, 4547, 4760, 4527, 4977, 5586, 5771, 5593, 5608, 5851, 5403, 5342, 4766, 5537, 4457, 4996, 5706, 5772, 5404, 4668, 5346, 5382, 5109, 5421, 4401, 4970, 4874, 5512, 4450, 5663, 4140, 5604, 5263, 5311, 5231, 5886, 4197, 4119, 4368, 4536, 4811, 5931, 5696, 4803, 5766, 5679, 4217, 4939, 4074, 5896, 4899, 4375, 5844, 4850, 5435, 5400, 5825, 5066, 4148, 4621, 4181, 4031, 4040, 5751, 5053, 4011, 5922, 5833, 4099, 4244, 4251, 5846, 5857, 4714, 4086, 5562).collect(Collectors.toList());
    Map<Integer, Boolean> XY = addPositiveSamples(X);

    Integer X_MIN = X.stream().min(Integer::compareTo).get();
    Integer X_MAX = X.stream().max(Integer::compareTo).get();

    int MU = 5049;
    int SIGMA2 = 414122;
    double EPSILON = 0.000000619922679071d;
    double EPSILON_DELTA = 0.0000000000001d;

    //Random random = new Random();
    //List<Integer> XX = IntStream.range(0,100).boxed().map(i-> random.nextInt(2000) + 4000).collect(Collectors.toList());

}
