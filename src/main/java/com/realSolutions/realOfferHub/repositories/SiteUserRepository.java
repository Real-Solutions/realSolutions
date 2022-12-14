package com.realSolutions.realOfferHub.repositories;

import com.realSolutions.realOfferHub.models.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteUserRepository extends JpaRepository<SiteUser, Long> {

    public SiteUser findByUsername(String username);
}
