package com.realSolutions.realOfferHub.controllers;
import com.realSolutions.realOfferHub.models.Offer;
import com.realSolutions.realOfferHub.models.Property;
import com.realSolutions.realOfferHub.models.SiteUser;
import com.realSolutions.realOfferHub.repositories.MessageRepository;
import com.realSolutions.realOfferHub.repositories.OfferRepository;
import com.realSolutions.realOfferHub.repositories.PropertyRepository;
import com.realSolutions.realOfferHub.repositories.SiteUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class PropertyController {

    @Autowired
    SiteUserRepository siteUserRepository;

    @Autowired
    PropertyRepository propertyRepository;

    @Autowired
    OfferRepository offerRepository;

    @Autowired
    MessageRepository messageRepository;

    @GetMapping("/newListing")
    public String addListing(String username, String accountStatus, Model m, Principal p){
        boolean status = Boolean.parseBoolean(accountStatus);
        SiteUser siteUser = siteUserRepository.findByUsername(p.getName());
        m.addAttribute("siteUser", siteUser);
        m.addAttribute("status", status);
        if(status) {
            m.addAttribute("username", username);
        }
        return "listing";
    }

    @DeleteMapping("/deleteProperty")
    public RedirectView deleteProperty(String id){
        Long idL = Long.parseLong(id);
        Property property = propertyRepository.getReferenceById(idL);
        for(Offer offer : property.getOffers()){
            messageRepository.deleteAll(offer.getMessages());
            offerRepository.delete(offer);
        }
        propertyRepository.delete(property);
        return new RedirectView("/dashboard");
    }

}
