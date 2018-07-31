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
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;

    @RequestMapping(value = "/topics/{id}", method = RequestMethod.GET)
    public String topics(
            @PathVariable(value = "id", required = false) String idStr,
            ModelMap modelMap) {

        String regex = "\\d+";
        if (idStr != null && idStr.matches(regex)) {
            Integer id = Integer.parseInt(idStr);
            Topic topic = topicRepository.findById(id);

            if (topic != null) {
                modelMap.addAttribute("topic", topic);
                modelMap.addAttribute("questions", questionRepository.findAllByTopicId(id));

                return "topic";
            }
        }
        return "redirect:/";
    }

}