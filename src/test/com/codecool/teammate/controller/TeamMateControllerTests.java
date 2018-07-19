package com.codecool.teammate.controller;


import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TeamMateControllerTests {

   /* @Configuration
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
    }*/

    private TeamMateController teamMateController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        teamMateController = new TeamMateController();
        mockMvc = MockMvcBuilders.standaloneSetup(teamMateController).build();
    }

    @Test
    public void indexLoads() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

}










