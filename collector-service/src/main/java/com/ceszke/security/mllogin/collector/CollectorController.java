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

    @GetMapping("/{sessionId}")
    @ResponseStatus(OK)
    public List<Integer> getSamples(@PathVariable String sessionId) {
        return collectorService.getSamples(sessionId);
    }

    @GetMapping("/ready/{sessionId}")
    @ResponseStatus(OK)
    public boolean isReadyToDetect(@PathVariable String sessionId) {
        return collectorService.isReadyToDetect(sessionId);
    }

    @PostMapping("/{sessionId}")
    @ResponseStatus(CREATED)
    public void collect(@RequestBody int speed, @PathVariable String sessionId) {
        collectorService.collect(speed, sessionId);
    }

    @GetMapping("/needed-samples/{sessionId}")
    @ResponseStatus(OK)
    public int getNumberOfNeededSamples(@PathVariable String sessionId) {
        return collectorService.getNumberOfNeededSamples(sessionId);
    }
}
