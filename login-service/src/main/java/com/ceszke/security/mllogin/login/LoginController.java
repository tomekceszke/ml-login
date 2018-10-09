package com.ceszke.security.mllogin.login;

import com.ceszke.security.mllogin.client.CollectorClient;
import com.ceszke.security.mllogin.client.LearningClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
@AllArgsConstructor
public class LoginController {

    private CollectorClient collectorClient;

    private LearningClient learningClient;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/delete")
    @ResponseBody
    public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String sessionId = request.getSession().getId();
        collectorClient.deleteAll(sessionId);
        learningClient.deleteLearnedModel(sessionId);
        SecurityContextHolder.clearContext();
        response.sendRedirect("/");
    }


}
