package com.realSolutions.realOfferHub.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String body;
    private Date date;

    @ManyToOne
    Offer myOffer;

    public Message() {
    }

    public Message(String body, Date date, Offer myOffer) {
        this.body = body;
        this.date = date;
        this.myOffer = myOffer;
    }

    public Long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public Date getDate() {
        return date;
    }

    public Offer getMyOffer() {
        return myOffer;
    }
}
