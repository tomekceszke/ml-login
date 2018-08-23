package com.ceszke.security.mllogin.client;


import com.ceszke.security.mllogin.dto.LearnedModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "learning-service", url = "http://127.0.0.1:8083")
public interface LearningClient {

    @GetMapping("/model")
    LearnedModel getLearnedModel();


}
