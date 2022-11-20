package com.realSolutions.realOfferHub.controllers;

import com.realSolutions.realOfferHub.models.Message;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;

@Controller
public class OfferController {

    @Autowired
    SiteUserRepository siteUserRepository;

    @Autowired
    PropertyRepository propertyRepository;

    @Autowired
    OfferRepository offerRepository;

    @Autowired
    MessageRepository messageRepository;

    @GetMapping("/offer")
    public String getOffer(Principal p, Model m){
        SiteUser agent = siteUserRepository
                .findByUsername(p.getName());
        m.addAttribute("username", p.getName());
        ArrayList<String> addresses = new ArrayList<>();
        for (SiteUser seller : agent.getSellers()){
            for(Property property : seller.getProperties()){
                addresses.add(property.getAddress());
            }
        }
        m.addAttribute("addresses", addresses);
        return "offer";
    }

    @PostMapping("/offer")
    public RedirectView newOffer(String address, String price, String downPayment, String contingentBuyer){
        Property property = propertyRepository.getPropertyByAddress(address);
        float pricef = Float.parseFloat(price);
        float downPaymentf = Float.parseFloat(downPayment);
        boolean contingentBuyerb = !contingentBuyer.equals("no");
        Offer newOffer = new Offer(address, pricef, downPaymentf, contingentBuyerb, property);
        offerRepository.save(newOffer);
        return new RedirectView("/dashboard");
    }

    @DeleteMapping("/deleteOffer")
    public RedirectView deleteOffer(String id){
        Long idL = Long.parseLong(id);
        Offer offer = offerRepository.getReferenceById(idL);
        messageRepository.deleteAll(offer.getMessages());
        offerRepository.delete(offer);
        return new RedirectView("/dashboard");
    }
}
