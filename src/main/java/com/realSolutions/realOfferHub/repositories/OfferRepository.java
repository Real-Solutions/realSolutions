package com.realSolutions.realOfferHub.repositories;

import com.realSolutions.realOfferHub.models.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, Long> {

}
