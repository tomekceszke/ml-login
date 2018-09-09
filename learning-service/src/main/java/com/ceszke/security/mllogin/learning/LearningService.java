package com.ceszke.security.mllogin.learning;

import com.ceszke.security.mllogin.client.CollectorClient;
import com.ceszke.security.mllogin.dto.GaussianDistribution;
import com.ceszke.security.mllogin.dto.LearnedModelDto;
import com.ceszke.security.mllogin.math.gaussian.GaussianUtils;
import com.ceszke.security.mllogin.math.threshold.ThresholdUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.ceszke.security.mllogin.math.data.DataUtils.addPositiveSamples;

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
        Collections.shuffle(samples);
        int size = samples.size();
        int toIndex = size - (size / 3);
        List<Integer> trainingData = samples.subList(0, toIndex);
        List<Integer> crossValidationData = samples.subList(toIndex, size);
        Map<Integer, Boolean> crossValidationDataMap = addPositiveSamples(crossValidationData);
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



}
