package com.ceszke.security.mllogin.collector;

import com.ceszke.security.mllogin.dto.SampleDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CollectorService {

    private final CollectorRepository collectorRepository;

    @Value("${ml.required.samples}")
    private int requiredSamples;

    public boolean isReadyToDetect() {
        return collectorRepository.count() >= requiredSamples;
    }

    public void collect(int speed) {
        if (speed > 0) {
            collectorRepository.save(Sample.builder().speed(speed).build());
        } else {
            throw new IllegalArgumentException("Speed cannot be negative or zero");
        }
    }

    public Set<SampleDto> getSamples() {
        return collectorRepository.findAll().stream().map(sample -> new ModelMapper().map(sample, SampleDto.class)).collect(Collectors.toSet());
    }

}
