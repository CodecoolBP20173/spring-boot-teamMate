package com.codecool.teammate.controller;


import com.codecool.teammate.repository.QuestionRepository;
import com.codecool.teammate.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class TeamMateController {

    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/")
    public String index(@RequestParam(value = "searched_string", required = false) String searchedString,
            ModelMap modelMap) {

        if (searchedString != null) {
            modelMap.addAttribute("searched_string", searchedString);
            modelMap.addAttribute("searched_questions", questionRepository.findQuestionsByTitleContaining(searchedString) );
        } else {
            modelMap.addAttribute("topics", topicRepository.findAll());
        }
        return "index";
    }
}
