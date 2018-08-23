package com.ceszke.security.mllogin.security;

import com.ceszke.security.mllogin.client.MLValidatorClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@AllArgsConstructor
public class MLUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private MLValidatorClient mlValidatorClient;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        Authentication authentication = super.attemptAuthentication(request, response);

        int speed = Integer.parseInt(request.getParameter("speed"));

        boolean valid = mlValidatorClient.validate(speed);

        if (!valid) {
            throw new MLAuthenticationException("Invalid ML factor: speed");
        }

        return authentication;
    }
}
