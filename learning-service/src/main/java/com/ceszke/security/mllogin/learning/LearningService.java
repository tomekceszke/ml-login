package com.ceszke.security.mllogin.learning;

import com.ceszke.security.mllogin.client.CollectorClient;
import com.ceszke.security.mllogin.dto.GaussianDistribution;
import com.ceszke.security.mllogin.dto.LearnedModelDto;
import com.ceszke.security.mllogin.dto.SampleDto;
import com.ceszke.security.mllogin.math.gaussian.GaussianDistributionUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LearningService {

    @SuppressWarnings("unused")
    private CollectorClient collectorClient;

    private final LearningRepository learningRepository;

    public LearnedModelDto getLearnedModel() {
        LearnedModel learnedModel = learningRepository.findAll().stream().reduce((first, second) -> second).orElse(null);
        if (learnedModel == null) {
            return null;
        }
        return new ModelMapper().map(learnedModel, LearnedModelDto.class);
    }

    public void learn() {
        List<SampleDto> sampleDtos = collectorClient.getSamples();
        int[] sample = sampleDtos.stream().map(SampleDto::getSpeed).mapToInt(x -> x).toArray();
        GaussianDistribution gaussianDistribution = GaussianDistributionUtils.getGaussianDistribution(sample);
        learningRepository.save(LearnedModel.builder().mu(gaussianDistribution.getMu()).sigma2(gaussianDistribution.getSigma2()).epsilon(0.05).build()); // TODO learn epsilon
    }
}
