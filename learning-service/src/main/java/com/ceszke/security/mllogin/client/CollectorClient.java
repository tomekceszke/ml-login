package com.ceszke.security.mllogin.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "collector-service", url = "${collector-service.ribbon.listOfServers:localhost}")
public interface CollectorClient {

    @GetMapping("/ready")
    boolean isReadyToDetect();

    @GetMapping("/")
    List<Integer> getSamples();
}
