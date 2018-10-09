package com.ceszke.security.mllogin.collector;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Random;

import static com.ceszke.security.mllogin.collector.CollectorTestUtils.TEST_SESSION_ID;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CollectorControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CollectorRepository collectorRepository;

    @Value("${ml.required.samples}")
    private int requiredSamples;

    @After
    public void tearDown() throws Exception {
        collectorRepository.deleteAll();
    }

    @Test
    public void collect() throws Exception {
        mvc.perform(post("/{sessionId}",TEST_SESSION_ID)
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(100)))
                .andDo(print())
                .andExpect(status().isCreated());

        mvc.perform(post("/{sessionId}",TEST_SESSION_ID)
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(-100)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void isReadyToDetect() throws Exception {
        addRandomSamples(requiredSamples);
        mvc.perform(get("/ready/{sessionId}",TEST_SESSION_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void getSamples() throws Exception {
        addRandomSamples(requiredSamples);
        mvc.perform(get("/{sessionId}",TEST_SESSION_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(requiredSamples)));
    }

    @Test
    public void deleteAllSamples() throws Exception {
        mvc.perform(get("/{sessionId}",TEST_SESSION_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(0)));
        addRandomSamples(requiredSamples);
        mvc.perform(get("/{sessionId}",TEST_SESSION_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(requiredSamples)));
        mvc.perform(delete("/{sessionId}",TEST_SESSION_ID))
                .andExpect(status().isNoContent());
        mvc.perform(get("/{sessionId}",TEST_SESSION_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(0)));
    }

    private void addRandomSamples(int numberOfSamples) throws Exception {
        for (int i = 0; i < numberOfSamples; i++) {
            addRandomSample();
        }
    }

    private void addRandomSample() throws Exception {
        Random random = new Random();
        int randomSpeed = random.nextInt(1000) + 2000;
        mvc.perform(post("/{sessionId}",TEST_SESSION_ID)
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(randomSpeed)))
                .andExpect(status().isCreated());
    }


}