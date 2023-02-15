package com.paragon.uberdeluxe.data.repositories;

import com.paragon.uberdeluxe.data.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {
}
