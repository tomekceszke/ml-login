package com.ceszke.security.mllogin.validator.password;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static com.ceszke.security.mllogin.path.Paths.LoginValidatorPaths.PasswordTypingTimeValidatorPaths.passwordTypingTimeValidator;
import static com.ceszke.security.mllogin.path.Paths.LoginValidatorPaths.PasswordTypingTimeValidatorPaths.typingTimeParam;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
@NoArgsConstructor
public class PasswordTypingTimeValidatorControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void isPasswordValid() throws Exception {
        String primeString = mockMvc.perform(get(passwordTypingTimeValidator).param(typingTimeParam, ""+2000))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        boolean isValid = objectMapper.readValue(primeString, boolean.class);
        assertTrue(isValid);
    }
}