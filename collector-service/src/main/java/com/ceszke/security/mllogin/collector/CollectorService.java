package com.ceszke.security.mllogin.collector;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CollectorService {

    private final CollectorRepository collectorRepository;

    @Value("${ml.required.samples}")
    private int requiredSamples;

    public boolean isReadyToDetect(String sessionId) {
        return collectorRepository.countBySessionId(sessionId) >= requiredSamples;
    }

    @Transactional
    public void collect(int speed, String sessionId) {
        if (speed > 0) {
            collectorRepository.save(Sample.builder().sessionId(sessionId).speed(speed).build());
        } else {
            throw new IllegalArgumentException("Speed cannot be negative or zero");
        }
    }

    public List<Integer> getSamples(String sessionId) {
        return collectorRepository.findAllBySessionId(sessionId).stream().map(Sample::getSpeed).collect(Collectors.toList());
    }

    public int getNumberOfNeededSamples(String sessionId) {
        int neededSamples = (int) (requiredSamples - collectorRepository.countBySessionId(sessionId));
        return neededSamples < 0 ? 0 : neededSamples;
    }

    @Transactional
    public void deleteAll(String sessionId) {
        collectorRepository.deleteBySessionId(sessionId);
    }
}
