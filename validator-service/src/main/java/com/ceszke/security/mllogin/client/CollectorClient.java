package com.ceszke.security.mllogin.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "collector-service", url = "${collector-service.ribbon.listOfServers:localhost}")
public interface CollectorClient {

    @GetMapping("/{sessionId}")
    List<Integer> getSamples(@PathVariable("sessionId") String sessionId);

    @GetMapping("/ready/{sessionId}")
    boolean isReadyToLearn(@PathVariable("sessionId") String sessionId);

    @PostMapping("/{sessionId}")
    void collect(@RequestBody int speed, @PathVariable("sessionId") String sessionId);
}
