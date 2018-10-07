package com.ceszke.security.mllogin.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "validator-service", url = "${validator-service.ribbon.listOfServers:localhost}")
public interface ValidatorClient {

    @GetMapping("/{speed}/{sessionId}")
    boolean validate(@PathVariable("speed") int speed, @PathVariable("sessionId") String sessionId);
}
