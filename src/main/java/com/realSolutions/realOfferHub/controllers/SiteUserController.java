package com.realSolutions.realOfferHub.controllers;

import com.realSolutions.realOfferHub.models.Property;
import com.realSolutions.realOfferHub.models.SiteUser;
import com.realSolutions.realOfferHub.repositories.PropertyRepository;
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
    PropertyRepository propertyRepository;

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

    @GetMapping("/dashboard")
    public String getDashboardPage(){return "dashboard";}

    @GetMapping("/newListing")
    public String getNewListing(){return "listing";}

    @PostMapping("/signup")
    public RedirectView createUser(String username, String password, String firstName, String lastName){
        String hashedPW = passwordEncoder.encode(password);
        SiteUser newUser = new SiteUser("Jon", "Snow", username, hashedPW, "agent", "5554443333", "abc@gmail.com", "Abc Street", "Offer up", "272736", "an agent seller" );
        siteUserRepository.save(newUser);
        authWithHttpServletRequest(username, password);
        return new RedirectView("/");
    }

    @PostMapping("/newListing")
    public RedirectView createListing(String address, String price, String date, String sellerUserName, String password, Principal p){
        SiteUser agent = siteUserRepository.findByUsername(p.getName());
        String hashedPW = passwordEncoder.encode(password);
        SiteUser newUser = new SiteUser("Jon", "Snow", sellerUserName, hashedPW, "seller", "5554443333", "abc@gmail.com", "Abc Street", "Offer up", "272736", "a seller", agent);
        siteUserRepository.save(newUser);
        authWithHttpServletRequest(sellerUserName, password);
        Property newProperty = new Property(address, Float.parseFloat(price), newUser);
        propertyRepository.save(newProperty);
        return new RedirectView("/dashboard");
    }

    public void authWithHttpServletRequest(String username, String password){
        try {
            request.login(username, password);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

}
