package com.paragon.uberdeluxe.data.repositories;

import com.paragon.uberdeluxe.data.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
