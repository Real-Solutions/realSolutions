package com.realSolutions.realOfferHub.repositories;

import com.realSolutions.realOfferHub.models.Message;
import com.realSolutions.realOfferHub.models.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
