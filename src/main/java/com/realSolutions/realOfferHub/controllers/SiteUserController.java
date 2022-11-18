package com.realSolutions.realOfferHub.controllers;

import com.realSolutions.realOfferHub.models.SiteUser;
import com.realSolutions.realOfferHub.repositories.SiteUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class SiteUserController {

    @Autowired
    SiteUserRepository siteUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    HttpServletRequest request;

    @GetMapping("login")
    public String getLoginPage(){
        return "login";
    }

    @GetMapping("/signup")
    public String getSignUpPage(){
        return "signup";
    }

    @PostMapping("/signup")
    public RedirectView createUser(String username, String password, String firstName, String lastName){
        String hashedPW = passwordEncoder.encode(password);
        SiteUser newUser = new SiteUser("Jon", "Snow", username, hashedPW, "agent", "5554443333", "abc@gmail.com", "Abc Street", "Offer up", "272736", "an agent seller" );
        siteUserRepository.save(newUser);
        authWithHttpServletRequest(username, password);
        return new RedirectView("/");
    }


    public void authWithHttpServletRequest(String username, String password){
        try {
            request.login(username, password);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

}
