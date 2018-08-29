package com.ceszke.security.mllogin.learning;

import com.ceszke.security.mllogin.client.CollectorClient;
import com.ceszke.security.mllogin.dto.GaussianDistribution;
import com.ceszke.security.mllogin.dto.LearnedModelDto;
import com.ceszke.security.mllogin.dto.SampleDto;
import com.ceszke.security.mllogin.math.gaussian.GaussianUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

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
        List<SampleDto> sampleDtos = collectorClient.getSamples();
        int[] sample = sampleDtos.stream().map(SampleDto::getSpeed).mapToInt(x -> x).toArray();
        GaussianDistribution gaussianDistribution = GaussianUtils.getGaussianDistribution(sample);
        learningRepository.save(LearnedModel.builder().mu(gaussianDistribution.getMu()).sigma2(gaussianDistribution.getSigma2()).epsilon(0.05).build()); // TODO learn epsilon
    }




}
