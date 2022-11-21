package com.realSolutions.realOfferHub.controllers;

import com.realSolutions.realOfferHub.models.Offer;
import com.realSolutions.realOfferHub.models.Property;
import com.realSolutions.realOfferHub.models.SiteUser;
import com.realSolutions.realOfferHub.repositories.MessageRepository;
import com.realSolutions.realOfferHub.repositories.OfferRepository;
import com.realSolutions.realOfferHub.repositories.PropertyRepository;
import com.realSolutions.realOfferHub.repositories.SiteUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import org.unbescape.properties.PropertiesKeyEscapeLevel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SiteUserController {

    @Autowired
    SiteUserRepository siteUserRepository;

    @Autowired
    PropertyRepository propertyRepository;

    @Autowired
    OfferRepository offerRepository;

    @Autowired
    MessageRepository messageRepository;

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
    public String getDashboardPage(Principal p, Model m){
        SiteUser siteUser = siteUserRepository.findByUsername(p.getName());
        m.addAttribute("siteUser", siteUser);
        if(siteUser.getRole().equals("seller")) {
            ArrayList<Offer> offers = new ArrayList<>();
            for(Property property : siteUser.getProperties()) {
                offers.addAll(property.getOffers());
            }
            m.addAttribute("offers", offers);
            return "dashboard";
        }
        List<SiteUser> sellers = siteUser.getSellers();
        m.addAttribute("sellers", sellers);
        return "dashboard";
    }

//    @GetMapping("/newListing")
//    public String getNewListing(){return "listing";}

    @PostMapping("/signup")
    public RedirectView createUser(String firstName, String lastName, String username, String password,  String role, String phoneNumber, String email, String brokerageName, String licenseNumber, String bio){
        String hashedPW = passwordEncoder.encode(password);
        SiteUser newUser = new SiteUser(firstName, lastName, username, hashedPW, "agent", phoneNumber, email, brokerageName, licenseNumber, bio );
        siteUserRepository.save(newUser);
        authWithHttpServletRequest(username, password);
        return new RedirectView("/");
    }

    @PostMapping("/newListing")
    public RedirectView createListing(String firstName, String lastName, String phoneNumber, String email,String address, String price, String sellerUserName, String password, String accountStatus, Principal p, String initialPosting, String numberOfRooms, String numberOfBathrooms, String squareFootage, String yearBuilt) throws ParseException {

        boolean status = Boolean.parseBoolean(accountStatus);
        if(!status){
            SiteUser agent = siteUserRepository.findByUsername(p.getName());
            String hashedPW = passwordEncoder.encode(password);
            SiteUser newSeller = new SiteUser(firstName, lastName, sellerUserName, hashedPW, "seller", phoneNumber, email, "n/a", "N/a", "n/a", agent);
            siteUserRepository.save(newSeller);
        }
        SiteUser seller = siteUserRepository.findByUsername(sellerUserName);
        Property newProperty = new Property(address, Float.parseFloat(price), new SimpleDateFormat("yyyy-MM-dd").parse(initialPosting), Integer.parseInt(numberOfRooms), Integer.parseInt(numberOfBathrooms), Float.parseFloat(squareFootage), new SimpleDateFormat("yyyy-MM-dd").parse(yearBuilt), seller);
        propertyRepository.save(newProperty);
        return new RedirectView("/dashboard");
    }

    @DeleteMapping("/deleteSeller")
    public RedirectView deleteSeller(String id){
        Long idL = Long.parseLong(id);
        SiteUser siteUser = siteUserRepository.getReferenceById(idL);
        for(Property property : siteUser.getProperties()){
            for(Offer offer : property.getOffers()){
                messageRepository.deleteAll(offer.getMessages());
                offerRepository.delete(offer);
            }
            propertyRepository.delete(property);
        }
        siteUserRepository.delete(siteUser);
        return new RedirectView("/dashboard");
    }

    //This method signs the user in, however, if there is already a user signed-in then the method returns an exception, i.e., "printStackTrace()", which is seen in red in the run time feed
    public void authWithHttpServletRequest(String username, String password){
        try {
            request.login(username, password);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }


}
