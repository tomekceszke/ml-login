package com.ceszke.security.mllogin.debug;

import com.ceszke.security.mllogin.client.CollectorClient;
import com.ceszke.security.mllogin.client.LearningClient;
import com.ceszke.security.mllogin.dto.LearnedModelDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DebugService {

    final private LearningClient learningClient;

    final private CollectorClient collectorClient;

    private boolean invalidSpeed;

    public int getNumberOfNeededSamples() {
        return collectorClient.getNumberOfNeededSamples();
    }

    public LearnedModelDto getModel() {
        return learningClient.getLearnedModel();
    }

    public boolean isInvalidSpeed() {
        return invalidSpeed;
    }

    public void setInvalidSpeed(boolean invalidSpeed) {
        this.invalidSpeed = invalidSpeed;
    }
}
