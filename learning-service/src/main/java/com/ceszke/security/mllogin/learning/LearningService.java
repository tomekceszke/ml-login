package com.ceszke.security.mllogin.learning;

import com.ceszke.security.mllogin.client.CollectorClient;
import com.ceszke.security.mllogin.dto.GaussianDistribution;
import com.ceszke.security.mllogin.dto.LearnedModelDto;
import com.ceszke.security.mllogin.math.gaussian.GaussianUtils;
import com.ceszke.security.mllogin.math.threshold.ThresholdUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LearningService {

    private CollectorClient collectorClient;

    private LearningRepository learningRepository;

    public LearnedModelDto getLearnedModel() {
        return learningRepository.findAll().stream()
                .reduce((first, second) -> second)
                .map(learnedModel -> new ModelMapper().map(learnedModel, LearnedModelDto.class))
                .orElse(null);
    }

    public void learn() {
        // prepare samples
        List<Integer> samples = collectorClient.getSamples();
        int toIndex = samples.size() / 2;
        List<Integer> trainingData = samples.subList(0, toIndex);
        List<Integer> crossValidationData = samples.subList(toIndex, samples.size());
        Map<Integer, Boolean> crossValidationDataMap = completeCvData(crossValidationData);
        // get gaussianDistribution for trainingData
        GaussianDistribution gaussianDistribution = GaussianUtils.getGaussianDistribution(trainingData);
        // get epsilon
        double epsilon = selectEpsilon(crossValidationDataMap, gaussianDistribution);
        // save model
        learningRepository.save(LearnedModel.builder().mu(gaussianDistribution.getMu()).sigma2(gaussianDistribution.getSigma2()).epsilon(epsilon).build());
    }

    private double selectEpsilon(Map<Integer, Boolean> crossValidationDataMap, GaussianDistribution gaussianDistribution) {
        Map<Double, Boolean> crossValidationProbabilityData = crossValidationDataMap.entrySet().stream()
                .collect(Collectors.toMap(entry -> GaussianUtils.getProbability(gaussianDistribution, entry.getKey()), Map.Entry::getValue, (p1, p2) -> p1));
        return ThresholdUtils.selectEpsilon(crossValidationProbabilityData);
    }

    private Map<Integer, Boolean> completeCvData(List<Integer> crossValidationData) {
        Map<Integer, Boolean> trainingData = crossValidationData.stream().collect(Collectors.toMap(p -> p, p -> false));
        // impossible low values
        Integer min = crossValidationData.stream().min(Integer::compareTo).get();
        trainingData.put(0, true);
        trainingData.put(100, true);
        trainingData.put(500, true);
        trainingData.put(1000, true);
        trainingData.put(min/2, true);
        // high values
        Integer max = crossValidationData.stream().max(Integer::compareTo).get();
        trainingData.put(max * 2, true);
        trainingData.put(max + 30000, true);
        trainingData.put(max + 60000, true);
        return trainingData;
    }


}
