package com.ceszke.security.mllogin.login;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Slf4j
@Controller
public class LoginController {

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model, Principal principal) {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //model.addAttribute()
        return "index";
    }


}
