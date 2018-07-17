package com.codecool.teammate.repository;

import com.codecool.teammate.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

// This is how you define basic CRUD operations on the Customer entity
public interface QuestionRepository extends JpaRepository<Answer, Long> {

}