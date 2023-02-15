package com.paragon.uberdeluxe.data.repositories;

import com.paragon.uberdeluxe.data.models.BankInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankInformationRepository extends JpaRepository<BankInformation, Long> {

}
