package com.ceszke.security.mllogin.client;


import com.ceszke.security.mllogin.dto.LearnedModelDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "learning-service", url = "${learning-service.ribbon.listOfServers:localhost}")
public interface LearningClient {

    @GetMapping("/model")
    LearnedModelDto getLearnedModel();

}
