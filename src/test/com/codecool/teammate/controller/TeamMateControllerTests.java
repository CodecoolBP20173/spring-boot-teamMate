package com.codecool.teammate.controller;


import com.codecool.teammate.model.Question;
import com.codecool.teammate.model.Topic;
import com.codecool.teammate.repository.QuestionRepository;
import com.codecool.teammate.repository.TopicRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class TeamMateControllerTests {
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private TeamMateController teamMateController;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private TopicRepository topicRepository;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        teamMateController = new TeamMateController();
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        topicRepository.deleteAll();
        questionRepository.deleteAll();
        topicRepository.save(Topic.create("Topic1"));
        topicRepository.save(Topic.create("Topic2"));
        questionRepository.save(Question.create("Test question title1"));
        questionRepository.save(Question.create("Test question title2"));
    }
    @Test
    public void indexLoads() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andDo(print());
    }

}


   /* @Configuration
    class DataSourceSetup {
        @Bean
        @Primary
        DataSource getDataSource() {
            return new EmbeddedDatabaseBuilder()
                    .setType(H2)
                    .build();
        }

    }*/










