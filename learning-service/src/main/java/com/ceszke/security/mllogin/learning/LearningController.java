package com.ceszke.security.mllogin.learning;

import com.ceszke.security.mllogin.dto.LearnedModel;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor
public class LearningController {

    private LearningService learningService;

    @GetMapping("/model")
    @ResponseStatus(OK)
    public LearnedModel getLearnedModel() {
        return learningService.getLearnedModel();
    }

    @PostMapping("/learn")
    @ResponseStatus(CREATED)
    public void learn() {
        learningService.learn();
    }
}
