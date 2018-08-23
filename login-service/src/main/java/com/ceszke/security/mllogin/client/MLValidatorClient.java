package com.ceszke.security.mllogin.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "ml-validator-service", url = "http://127.0.0.1:8081")
public interface MLValidatorClient {

    @PostMapping("/")
    boolean validate(@RequestBody int speed);
}
