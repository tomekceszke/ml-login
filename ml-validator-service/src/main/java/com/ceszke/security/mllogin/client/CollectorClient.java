package com.ceszke.security.mllogin.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "collector-service", url = "http://127.0.0.1:8082")
public interface CollectorClient {

    @GetMapping("/ready")
    boolean isReadyToLearn();

    @PostMapping("/")
    void collect(@RequestBody int speed);
}
