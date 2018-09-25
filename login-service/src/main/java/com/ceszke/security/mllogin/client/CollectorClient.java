package com.ceszke.security.mllogin.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "collector-service", url = "${collector-service.ribbon.listOfServers:localhost}")
public interface CollectorClient {

    @GetMapping("/needed-samples")
    int getNumberOfNeededSamples();




}
