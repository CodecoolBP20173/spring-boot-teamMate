package com.codecool.teammate.dao;

import com.codecool.teammate.model.Answer;
import com.codecool.teammate.model.Question;

import java.util.List;

public interface QuestionDAO {

    void add(Question question);
    Question find(int id);
    void remove(int id);


    List<Question> findAllQuestion();
    List<Question> findAllQuestionByTopic(int id);
    Answer findAnswerByQuestion(Question question);
}
