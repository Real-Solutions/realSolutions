package com.realSolutions.realOfferHub.models;

import javax.persistence.*;
import java.time.LocalTime;
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
    Date closeOfEscrow;
    String concessions;
    String loanType;
    boolean contingentBuyer;
    String personalPropertyRequested;
    String hoa;
    String homeWarranty;
    String inspectionPeriod;
    boolean escalation;
    Date responseDate;
    LocalTime responseTime;
    String additionalTermsAndConditions;
    String priceString;
    String downPaymentString;
    String ernestMoneyAmountString;


    @ManyToOne
    Property myProperty ;

    @OneToMany(mappedBy ="myOffer")
    List<Message> messages;

    public Offer() {
    }

    public Offer(float offerPrice, float downPayment, boolean contingentBuyer, Property myProperty, String buyersFirstName, String buyersLastName, float ernestMoneyAmount, Date closeOfEscrow, String concessions, String loanType, String personalPropertyRequested, String hoa, String homeWarranty, String inspectionPeriod, boolean escalation, Date responseDate, LocalTime responseTime, String additionalTermsAndConditions, String priceString, String downPaymentString, String ernestMoneyAmountString) {
        this.offerPrice = offerPrice;
        this.downPayment = downPayment;
        this.contingentBuyer = contingentBuyer;
        this.myProperty = myProperty;
        this.buyersFirstName = buyersFirstName;
        this.buyersLastName = buyersLastName;
        this.ernestMoneyAmount = ernestMoneyAmount;
        this.closeOfEscrow = closeOfEscrow;
        this.concessions = concessions;
        this.loanType = loanType;
        this.personalPropertyRequested = personalPropertyRequested;
        this.hoa = hoa;
        this.homeWarranty = homeWarranty;
        this.inspectionPeriod = inspectionPeriod;
        this.escalation = escalation;
        this.responseDate = responseDate;
        this.responseTime = responseTime;
        this.additionalTermsAndConditions = additionalTermsAndConditions;
        this.priceString = priceString;
        this.downPaymentString = downPaymentString;
        this.ernestMoneyAmountString = ernestMoneyAmountString;
    }



    public Long getId() {
        return id;
    }

    public Property getMyProperty() {
        return myProperty;
    }

    public String getDownPaymentString() {
        return downPaymentString;
    }


    public String getErnestMoneyAmountString() {
        return ernestMoneyAmountString;
    }

    public String getPriceString() {
        return priceString;
    }

    public String getBuyersLastName() {
        return buyersLastName;
    }

    public float getErnestMoneyAmount() {
        return ernestMoneyAmount;
    }

    public Date getCloseOfEscrow() {
        return closeOfEscrow;
    }

    public String getConcessions() {
        return concessions;
    }

    public String getLoanType() {
        return loanType;
    }

    public String getPersonalPropertyRequested() {
        return personalPropertyRequested;
    }

    public String getHoa() {
        return hoa;
    }

    public String getHomeWarranty() {
        return homeWarranty;
    }

    public String getInspectionPeriod() {
        return inspectionPeriod;
    }

    public boolean isEscalation() {
        return escalation;
    }

    public Date getResponseDate() {
        return responseDate;
    }

    public LocalTime getResponseTime() {
        return responseTime;
    }

    public String getAdditionalTermsAndConditions() {
        return additionalTermsAndConditions;
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

    public List<Message> getMessages() {
        return messages;
    }
}
