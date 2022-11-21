package com.realSolutions.realOfferHub.models;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

   private String address;
   private Float price;
   private Date initialPosting;
   private Integer numberOfRooms;
   private Integer numberOfBathrooms;
   private Float squareFootage;
   private Date yearBuilt;


    @ManyToOne
    SiteUser mySeller;

    @OneToMany(mappedBy = "myProperty")
    List<Offer> offers;

    protected Property() {
    }

    public Property(String address, Float price, Date initialPosting, Integer numberOfRooms, Integer numberOfBathrooms, Float squareFootage, Date yearBuilt, SiteUser mySeller) {
        this.address = address;
        this.price = price;
        this.initialPosting = initialPosting;
        this.numberOfRooms = numberOfRooms;
        this.numberOfBathrooms = numberOfBathrooms;
        this.squareFootage = squareFootage;
        this.yearBuilt = yearBuilt;
        this.mySeller = mySeller;
    }

    public Date getInitialPosting() {
        return initialPosting;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public Integer getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    public Float getSquareFootage() {
        return squareFootage;
    }

    public Date getYearBuilt() {
        return yearBuilt;
    }

    public List<Offer> getOffers() {
        return offers;
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
