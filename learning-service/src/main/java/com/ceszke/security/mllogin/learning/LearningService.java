package com.ceszke.security.mllogin.learning;

import com.ceszke.security.mllogin.dto.LearnedModel;
import org.springframework.stereotype.Service;

@Service
public class LearningService {

    public LearnedModel getLearnedModel() {
        return LearnedModel.builder().build();
    }

    public void learn() {

    }
}
