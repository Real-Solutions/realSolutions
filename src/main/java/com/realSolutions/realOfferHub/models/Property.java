package com.realSolutions.realOfferHub.models;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

   private String address;
   private Float price;

    @ManyToOne
    SiteUser mySeller;

    protected Property() {
    }

    public Property(String address, Float price, SiteUser mySeller) {
        this.address = address;
        this.price = price;
        this.mySeller = mySeller;
    }

    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public Float getPrice() {
        return price;
    }

    public SiteUser getMySeller() {
        return mySeller;
    }
}
