package com.realSolutions.realOfferHub.controllers;

import com.realSolutions.realOfferHub.models.Message;
import com.realSolutions.realOfferHub.models.Offer;
import com.realSolutions.realOfferHub.repositories.MessageRepository;
import com.realSolutions.realOfferHub.repositories.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;

@Controller
public class MessageController {

    @Autowired
    OfferRepository offerRepository;

    @Autowired
    MessageRepository messageRepository;

    @PostMapping("/newMessage")
    public RedirectView newMessage(String body, String id){
        Long idL = Long.parseLong(id);
        Offer offer = offerRepository.getReferenceById(idL);
        Date date = new Date();
        Message newMessage = new Message(body, date, offer);
        messageRepository.save(newMessage);
        return new RedirectView("/dashboard");
    }

    @DeleteMapping("/deleteMessage")
    public RedirectView deleteMessage(String id){
        Long idL = Long.parseLong(id);
        Message message = messageRepository.getReferenceById(idL);
        messageRepository.delete(message);
        return new RedirectView("/dashboard");
    }
}
