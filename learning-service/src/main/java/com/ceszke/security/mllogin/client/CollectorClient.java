package com.ceszke.security.mllogin.client;


import com.ceszke.security.mllogin.dto.SampleDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

@FeignClient(name = "collector-service", url = "http://127.0.0.1:8082")
public interface CollectorClient {

    @GetMapping("/ready")
    boolean isReadyToDetect();

    @GetMapping("/")
    Set<SampleDto> getSamples();
}
