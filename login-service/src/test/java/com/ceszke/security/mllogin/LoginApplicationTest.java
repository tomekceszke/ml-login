package com.ceszke.security.mllogin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginApplicationTest {


    @Test
    public void applicationContextLoaded() {
    }

    @Test
    public void applicationContextTest() {
        LoginApplication.main(new String[]{});
    }

}