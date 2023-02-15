package com.paragon.uberdeluxe.data.repositories;

import com.paragon.uberdeluxe.data.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
