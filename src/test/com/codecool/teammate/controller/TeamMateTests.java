package com.codecool.teammate.controller;


import com.codecool.teammate.model.Question;
import com.codecool.teammate.model.Topic;
import com.codecool.teammate.repository.QuestionRepository;
import com.codecool.teammate.repository.TopicRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TeamMateTests {

    @Configuration
    class DataSourceSetup {
        @Bean
        @Primary
        DataSource getDataSource() {
            return new EmbeddedDatabaseBuilder()
                    .setType(H2)
                    .build();
        }
    }
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    public void setup(){
        topicRepository.deleteAll();
        questionRepository.deleteAll();
        topicRepository.save(Topic.create("Topic1"));
        topicRepository.save(Topic.create("Topic2"));
        questionRepository.save(Question.create("Test question title1"));
        questionRepository.save(Question.create("Test question title2"));
    }

    @Test
    public void indexLoads() throws Exception {
        this.mvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}










