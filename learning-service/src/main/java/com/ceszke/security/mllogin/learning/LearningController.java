package com.ceszke.security.mllogin.learning;

import com.ceszke.security.mllogin.dto.LearnedModelDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor
public class LearningController {

    private LearningService learningService;

    @GetMapping("/model/{sessionId}")
    @ResponseStatus(OK)
    public LearnedModelDto getLearnedModel(@PathVariable String sessionId) {
        return learningService.getLearnedModel(sessionId);
    }

    @PostMapping("/learn/{sessionId}")
    @ResponseStatus(CREATED)
    public void learn(@RequestBody List<Integer> samples, @PathVariable String sessionId) {
        learningService.learn(samples, sessionId);
    }
}
