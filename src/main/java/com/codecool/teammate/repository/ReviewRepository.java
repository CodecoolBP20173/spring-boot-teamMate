package com.codecool.teammate.repository;

import com.codecool.teammate.model.Answer;
import com.codecool.teammate.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

// This is how you define basic CRUD operations on the Customer entity
public interface ReviewRepository extends JpaRepository<Review, Long> {

}