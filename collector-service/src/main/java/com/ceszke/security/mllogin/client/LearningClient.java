package com.ceszke.security.mllogin.client;


import com.ceszke.security.mllogin.dto.LearnedModelDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "learning-service", url = "http://127.0.0.1:8083")
public interface LearningClient {

    @PostMapping("/learn")
    void learn();

    @GetMapping("/model")
    LearnedModelDto getLearnedModel();
}
