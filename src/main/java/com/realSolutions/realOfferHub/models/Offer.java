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
    String hoa;
    String homeWarranty;
    String inspectionPeriod;
    boolean escalation;
    Date responseDate;
    Date responseTime;
    String additionalTermsAndConditions;


    @ManyToOne
    Property myProperty ;

    @OneToMany(mappedBy ="myOffer")
    List<Message> messages;

    public Offer() {
    }

    public Offer(String address, float price, float downPayment, boolean contingentBuyer, Property myProperty, String buyersFirstName, String buyersLastName, float ernestMoneyAmount, float closeOfEscrow, float concessions, String loanType, String personalPropertyRequested, String hoa, String homeWarranty, String inspectionPeriod, boolean escalation, Date responseDate, Date responseTime, String additionalTermsAndConditions) {
        this.buyersFirstName = buyersFirstName;
        this.buyersLastName = buyersLastName;
        this.offerPrice = offerPrice;
        this.ernestMoneyAmount = ernestMoneyAmount;
        this.downPayment = downPayment;
        this.closeOfEscrow = closeOfEscrow;
        this.concessions = concessions;
        this.loanType = loanType;
        this.contingentBuyer = contingentBuyer;
        this.personalPropertyRequested = personalPropertyRequested;
        this.hoa = hoa;
        this.homeWarranty = homeWarranty;
        this.inspectionPeriod = inspectionPeriod;
        this.escalation = escalation;
        this.responseDate = responseDate;
        this.responseTime = responseTime;
        this.additionalTermsAndConditions = additionalTermsAndConditions;
        this.myProperty = myProperty;
    }

    public Long getId() {
        return id;
    }

    public String getBuyersLastName() {
        return buyersLastName;
    }

    public float getErnestMoneyAmount() {
        return ernestMoneyAmount;
    }

    public float getCloseOfEscrow() {
        return closeOfEscrow;
    }

    public float getConcessions() {
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

    public Date getResponseTime() {
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
