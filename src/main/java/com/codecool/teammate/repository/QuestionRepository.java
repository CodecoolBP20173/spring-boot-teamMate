package com.codecool.teammate.repository;

import com.codecool.teammate.model.Answer;
import com.codecool.teammate.model.Question;
import com.codecool.teammate.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// This is how you define basic CRUD operations on the Customer entity
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findQuestionsByTitleContaining(String searchedString);
    List<Question> findAllByTopicId(int id);
}