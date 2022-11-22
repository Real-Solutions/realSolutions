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
import java.sql.Time;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

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
    public RedirectView newOffer(String buyersFirstName, String buyersLastName, String address, String price, String downPayment, String ernestMoneyAmount, String contingentBuyer, String closeOfEscrow, String concessions, String loanType, String personalPropertyRequested, String hoa, String homeWarranty, String inspectionPeriod, String escalation, String responseDate, String responseTime, String additionalTermsAndConditions) throws ParseException {

        Property property = propertyRepository.getPropertyByAddress(address);

        boolean contingentBuyerb = !contingentBuyer.equals("no");
        boolean escalationb = !escalation.equals("no");

        Date responseDated = new SimpleDateFormat("yyyy-MM-dd").parse(responseDate);
        LocalTime responseTimed = LocalTime.parse(responseTime);

        NumberFormat priceFormat = NumberFormat.getInstance();
        String priceString = "$" + priceFormat.format(Float.parseFloat(price));
        String downPaymentString = "$" + priceFormat.format(Float.parseFloat(downPayment));
        String ernestMoneyAmountString = "$" + priceFormat.format(Float.parseFloat(ernestMoneyAmount));
        String closeOfEscrowString = "$" + priceFormat.format(Float.parseFloat(closeOfEscrow));
        String concessionsString = "$" + priceFormat.format(Float.parseFloat(concessions));

        Offer newOffer = new Offer(Float.parseFloat(price), Float.parseFloat(downPayment), contingentBuyerb, property, buyersFirstName, buyersLastName, Float.parseFloat(ernestMoneyAmount), Float.parseFloat(closeOfEscrow), Float.parseFloat(concessions), loanType, personalPropertyRequested, hoa, homeWarranty, inspectionPeriod, escalationb, responseDated, responseTimed, additionalTermsAndConditions, priceString, downPaymentString, ernestMoneyAmountString, closeOfEscrowString, concessionsString);

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
