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
import java.util.ArrayList;
import java.util.List;

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
    public String getDashboardPage(Principal p, Model m){
        SiteUser siteUser = siteUserRepository.findByUsername(p.getName());
        List<SiteUser> sellers = siteUser.getSellers();
        m.addAttribute("sellers", sellers);

        return "dashboard";
    }

    @GetMapping("/offer")
    public String getOffer(Principal p, Model m){
        SiteUser agent = siteUserRepository
                .findByUsername(p.getName());
        m.addAttribute("username", p.getName());
        m.addAttribute("properties", agent.getSellers());
        ArrayList<String> addresses = new ArrayList<>();
        for (SiteUser seller : agent.getSellers()){
            for(Property property : seller.getProperties()){
                addresses.add(property.getAddress());
            }
        }
        return "offer";
    }

//    @GetMapping("/newListing")
//    public String getNewListing(){return "listing";}

    @PostMapping("/signup")
    public RedirectView createUser(String username, String password, String firstName, String lastName){
        String hashedPW = passwordEncoder.encode(password);
        SiteUser newUser = new SiteUser("Jon", "Snow", username, hashedPW, "agent", "5554443333", "abc@gmail.com", "Abc Street", "Offer up", "272736", "an agent seller" );
        siteUserRepository.save(newUser);
        authWithHttpServletRequest(username, password);
        return new RedirectView("/");
    }

    @PostMapping("/newListing")
    public RedirectView createListing(String address, String price, String date, String sellerUserName, String password, String accountStatus, Principal p){
        boolean status = Boolean.parseBoolean(accountStatus);
        if(!status){
            SiteUser agent = siteUserRepository.findByUsername(p.getName());
            String hashedPW = passwordEncoder.encode(password);
            SiteUser newSeller = new SiteUser("Jon", "Snow", sellerUserName, hashedPW, "seller", "5554443333", "abc@gmail.com", "Abc Street", "Offer up", "272736", "a seller", agent);
            siteUserRepository.save(newSeller);
        }
        SiteUser seller = siteUserRepository.findByUsername(sellerUserName);
        Property newProperty = new Property(address, Float.parseFloat(price), seller);
        propertyRepository.save(newProperty);
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
