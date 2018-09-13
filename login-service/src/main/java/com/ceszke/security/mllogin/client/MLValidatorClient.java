package com.ceszke.security.mllogin.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "ml-validator-service", url = "http://127.0.0.1:8081")
public interface MLValidatorClient {

    @GetMapping("/{speed}")
    boolean validate(@PathVariable("speed") int speed);
}
