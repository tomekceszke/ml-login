package com.ceszke.security.mllogin.validator;

import com.ceszke.security.mllogin.client.CollectorClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class ValidatorService {

    private CollectorClient collectorClient;

    public boolean validate(int speed) {
        if (collectorClient.isReadyToDetect()) {

            return (speed > 3000);
        }  else {
            log.warn("Not ready to predict");
            collectorClient.collect(speed);
            return true;
        }
    }

}
