package com.paragon.uberdeluxe.data.repositories;

import com.paragon.uberdeluxe.data.models.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}
