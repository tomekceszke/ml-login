package com.ceszke.security.mllogin.client;


import com.ceszke.security.mllogin.dto.LearnedModelDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "learning-service", url = "${learning-service.ribbon.listOfServers:localhost}")
public interface LearningClient {

    @GetMapping("/model/{sessionId}")
    LearnedModelDto getLearnedModel(@PathVariable("sessionId") String sessionId);

    @PostMapping("/learn/{sessionId}")
    void learn(@RequestBody List<Integer> samples, @PathVariable("sessionId") String sessionId);
}
