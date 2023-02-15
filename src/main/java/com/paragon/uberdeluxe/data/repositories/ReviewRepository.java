package com.paragon.uberdeluxe.data.repositories;

import com.paragon.uberdeluxe.data.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
