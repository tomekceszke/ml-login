package com.ceszke.security.mllogin.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "collector-service", url = "http://127.0.0.1:8082")
public interface CollectorClient {

    @GetMapping("/ready")
    boolean isReadyToDetect();

    @PostMapping("/")
    void collect(@RequestParam int speed);
}
