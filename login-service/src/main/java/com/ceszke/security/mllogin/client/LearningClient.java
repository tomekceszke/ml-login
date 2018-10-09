package com.ceszke.security.mllogin.client;


import com.ceszke.security.mllogin.dto.LearnedModelDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "learning-service", url = "${learning-service.ribbon.listOfServers:localhost}")
public interface LearningClient {

    @GetMapping("/model/{sessionId}")
    LearnedModelDto getLearnedModel(@PathVariable("sessionId") String sessionId);

    @DeleteMapping("/model/{sessionId}")
    void deleteLearnedModel(@PathVariable("sessionId") String sessionId);

}
