package com.codecool.teammate.controller;


import com.codecool.teammate.model.Answer;
import com.codecool.teammate.model.Question;
import com.codecool.teammate.model.Topic;
import com.codecool.teammate.repository.AnswerRepository;
import com.codecool.teammate.repository.QuestionRepository;
import com.codecool.teammate.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;


@Controller
public class IndexController {

    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;

    @GetMapping("/")
    public String index
            (@RequestParam(value = "searched_string", required = false) String searchedString,
             ModelMap modelMap) {

        modelMap.addAttribute("topics", topicRepository.findAll());

        if (searchedString != null) {
            modelMap.addAttribute("searched_string", searchedString);

            List<Question> searchResult = new ArrayList<>();

            String[] searchWords = searchedString.split(" ");

            for (int i = 0; i < searchWords.length; i++) {
                String word = searchWords[i].replaceAll("[^a-zA-Z0-9]", "");

                List<Question> currentSearchResult = questionRepository.findAllByTitleIgnoreCaseContaining(word);

                if (i == 0) {
                    searchResult.addAll(currentSearchResult);
                } else {
                    searchResult.retainAll(currentSearchResult);
                }
            }

            modelMap.addAttribute("searched_questions", searchResult);
        }

        return "index";
    }
}
