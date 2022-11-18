package com.realSolutions.realOfferHub.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String buyersFirstName;
    String buyersLastName;
    float offerPrice;
    float ernestMoneyAmount;
    float downPayment;
    float closeOfEscrow;
    float concessions;
    String loanType;
    boolean contingentBuyer;
    String personalPropertyRequested;
    String Hoa;
    String HomeWarranty;
    String InspectionPeriod;
    boolean Escalation;
    Date responseDate;
    Date responseTime;
    String AdditionalTermsAndConditions;


    @ManyToOne
    Property myProperty ;

    public Offer() {
    }

    public Offer(String buyersFirstName, float offerPrice, float downPayment, boolean contingentBuyer) {
        this.buyersFirstName = buyersFirstName;
        this.offerPrice = offerPrice;
        this.downPayment = downPayment;
        this.contingentBuyer = contingentBuyer;
    }

    public Long getId() {
        return id;
    }

    public String getBuyersFirstName() {
        return buyersFirstName;
    }

    public float getOfferPrice() {
        return offerPrice;
    }

    public float getDownPayment() {
        return downPayment;
    }

    public boolean isContingentBuyer() {
        return contingentBuyer;
    }
}
