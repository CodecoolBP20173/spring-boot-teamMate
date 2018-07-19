package com.codecool.teammate.repository;

import com.codecool.teammate.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// This is how you define basic CRUD operations on the Customer entity
public interface QuestionRepository extends JpaRepository<Question, Long> {

    Question findById(int id);
    List<Question> findAllByTitleIgnoreCaseContaining(String searchedString);
    List<Question> findAllByTopicId(int id);

}
