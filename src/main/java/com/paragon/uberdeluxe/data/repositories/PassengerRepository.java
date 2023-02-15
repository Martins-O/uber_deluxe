package com.paragon.uberdeluxe.data.repositories;

import com.paragon.uberdeluxe.data.models.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}
