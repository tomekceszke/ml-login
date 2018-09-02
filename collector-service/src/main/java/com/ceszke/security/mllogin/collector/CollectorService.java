package com.ceszke.security.mllogin.collector;

import com.ceszke.security.mllogin.client.LearningClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CollectorService {

    private final CollectorRepository collectorRepository;

    @SuppressWarnings("unused")
    private LearningClient learningClient;

    @Value("${ml.required.samples}")
    private int requiredSamples;

    public boolean isReadyToDetect() {
        return collectorRepository.count() >= requiredSamples;
    }

    public void collect(int speed) {
        if (speed > 0) {
//            if (isReadyToDetect()) {
//                log.warn("Already have all required samples");
//                if (learningClient.getLearnedModel() == null) {
//                    log.info("Learning...");
//                    learningClient.learn();
//                } else {
//                    log.info("Already learned");
//                }
//            } else {
            collectorRepository.save(Sample.builder().speed(speed).build());
//            }
        } else {
            throw new IllegalArgumentException("Speed cannot be negative or zero");
        }
    }

    public List<Integer> getSamples() {
        return collectorRepository.findAll().stream().map(Sample::getSpeed).collect(Collectors.toList());
    }

}
