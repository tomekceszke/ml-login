package com.ceszke.security.mllogin.typingtime;

import com.ceszke.security.mllogin.path.Paths;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TypingTimeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void collect() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/{"+Paths.TYPING_TIME_PARAM+"}", "1000"))
                .andExpect(status().isCreated());

        mvc.perform(MockMvcRequestBuilders.post("/{"+Paths.TYPING_TIME_PARAM+"}", "-1000"))
                .andExpect(status().isBadRequest());
    }
}