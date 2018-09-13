package com.ceszke.security.mllogin.collector;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor
public class CollectorController {

    private CollectorService collectorService;

    @GetMapping("/")
    @ResponseStatus(OK)
    public List<Integer> getSamples() {
        return collectorService.getSamples();
    }

    @GetMapping("/ready")
    @ResponseStatus(OK)
    public boolean isReadyToDetect() {
        return collectorService.isReadyToDetect();
    }

    @PostMapping("/")
    @ResponseStatus(CREATED)
    public void collect(@RequestBody int speed) {
        collectorService.collect(speed);
    }

    @GetMapping("/needed-samples")
    public int getNumberOfNeededSamples() {
        return collectorService.getNumberOfNeededSamples();
    }
}
