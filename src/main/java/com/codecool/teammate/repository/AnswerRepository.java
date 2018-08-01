package com.codecool.teammate.repository;

import com.codecool.teammate.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// This is how you define basic CRUD operations on the Customer entity
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    Answer findById(int id);

//    List<Answer> findByTitle(String title);
//    Todo -> refactor

}