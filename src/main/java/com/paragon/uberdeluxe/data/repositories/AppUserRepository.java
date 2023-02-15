package com.paragon.uberdeluxe.data.repositories;

import com.paragon.uberdeluxe.data.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
}
