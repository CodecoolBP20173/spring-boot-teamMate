package com.codecool.teammate.controller;

import com.codecool.teammate.model.Question;
import com.codecool.teammate.repository.QuestionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestQuestionRepository {


        private QuestionRepository questionRepository;
        @Autowired
        public void setQuestionRepository(QuestionRepository questionRepository) {
            this.questionRepository = questionRepository;
        }
        @Test
        public void testSaveProduct(){
            questionRepository.save(Question.create("Test question title1"));
        }
    }


