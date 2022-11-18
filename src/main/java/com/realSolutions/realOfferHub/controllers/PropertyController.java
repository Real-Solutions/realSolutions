package com.realSolutions.realOfferHub.controllers;
import com.realSolutions.realOfferHub.models.Property;
import com.realSolutions.realOfferHub.models.SiteUser;
import com.realSolutions.realOfferHub.repositories.PropertyRepository;
import com.realSolutions.realOfferHub.repositories.SiteUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class PropertyController {

    @Autowired
    SiteUserRepository siteUserRepository;

    @Autowired
    PropertyRepository propertyRepository;

    @GetMapping("/newListing")
    public String addListing(String username, String accountStatus, Model m){
        boolean status = Boolean.parseBoolean(accountStatus);
        m.addAttribute("status", status);
        if(status) {
            m.addAttribute("username", username);
        }
        return "listing";
    }

}
