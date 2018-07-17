package com.codecool.teammate.controller;


import com.codecool.teammate.repository.QuestionRepository;
import com.codecool.teammate.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TeamMateController {

    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/")
    public String index(
            @RequestParam("searched_string") String searchedString,
            ModelMap modelMap) {

        if (searchedString != null) {
            modelMap.addAttribute("searched_string");
            modelMap.addAttribute("searched_question", questionRepository.findQuestionsByTitleContaining(searchedString) );
        } else {
            modelMap.addAttribute("topics", topicRepository.findAll());
        }
        return "index";
    }
}
