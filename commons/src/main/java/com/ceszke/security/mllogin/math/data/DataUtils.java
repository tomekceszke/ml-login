package com.ceszke.security.mllogin.math.data;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DataUtils {

    public static Map<Integer, Boolean> addPositiveSamples(List<Integer> negativeSamples) {
        Map<Integer, Boolean> samples = negativeSamples.stream().collect(Collectors.toMap(p -> p, p -> false, (p1, p2) -> false));
        // impossible low values
        Integer min = negativeSamples.stream().min(Integer::compareTo).get();
        samples.put(0, true);
        samples.put(min/4, true);
        samples.put(min/3, true);
        samples.put(min/2, true);
        // high values
        Integer max = negativeSamples.stream().max(Integer::compareTo).get();
        samples.put(max * 2, true);
        samples.put(max * 3, true);
        samples.put(max * 4, true);
        return samples;
    }

}
