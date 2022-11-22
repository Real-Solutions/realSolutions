package com.realSolutions.realOfferHub.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class SiteUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String firstName;
    String lastName;
    String username;
    String password;
    String role;
    String phoneNumber;
    String email;
    String brokerageName;
    String licenseNumber;
    String bio;

    @OneToMany(mappedBy = "mySellerAgent")
    List<SiteUser> sellers;

    @OneToMany(mappedBy = "myOldSellerAgent")
    List<SiteUser> archivedSellers;

    @ManyToOne
    SiteUser mySellerAgent;

    @ManyToOne
    SiteUser myOldSellerAgent;

    @ManyToMany
    @JoinTable(
            name = "AgbuyerAndAgeller",
            joinColumns = {@JoinColumn(name = "AgBuyer")},
            inverseJoinColumns = {@JoinColumn(name = "AgSeller")}
    )
    Set<SiteUser> agentBuyers = new HashSet<>();

    @ManyToMany(mappedBy = "agentBuyers")
    Set<SiteUser> agentSellers = new HashSet<>();

    @OneToMany(mappedBy = "mySeller")
    List<Property> properties;


    protected SiteUser() {
    }

    public SiteUser(String firstName, String lastName, String username, String password, String role, String phoneNumber, String email, String brokerageName, String licenseNumber, String bio) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.brokerageName = brokerageName;
        this.licenseNumber = licenseNumber;
        this.bio = bio;
    }

    public SiteUser(String firstName, String lastName, String username, String password, String role, String phoneNumber, String email, String brokerageName, String licenseNumber, String bio, SiteUser mySellerAgent) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.brokerageName = brokerageName;
        this.licenseNumber = licenseNumber;
        this.bio = bio;
        this.mySellerAgent = mySellerAgent;
    }

    public List<SiteUser> getArchivedSellers() {
        return archivedSellers;
    }

    public void setArchivedSellers(List<SiteUser> archivedSellers) {
        this.archivedSellers = archivedSellers;
    }

    public SiteUser getMyOldSellerAgent() {
        return myOldSellerAgent;
    }

    public void setMyOldSellerAgent(SiteUser myOldSellerAgent) {
        this.myOldSellerAgent = myOldSellerAgent;
    }

    public List<Property> getProperties() {
        return properties;
    }


    public Long getId() {
        return id;
    }

    public List<SiteUser> getSellers() {
        return sellers;
    }

    public SiteUser getMySellerAgent() {
        return mySellerAgent;
    }

    public void setMySellerAgent(SiteUser mySellerAgent) {
        this.mySellerAgent = mySellerAgent;
    }

    public Set<SiteUser> getAgentBuyers() {
        return agentBuyers;
    }

    public Set<SiteUser> getAgentSellers() {
        return agentSellers;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRole() {
        return role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getBrokerageName() {
        return brokerageName;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public String getBio() {
        return bio;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
