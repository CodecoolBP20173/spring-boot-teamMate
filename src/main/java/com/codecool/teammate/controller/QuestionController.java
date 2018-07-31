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
public class QuestionController {

    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;

    @GetMapping("/questions/{id}")
    public String question(
            @PathVariable("id") String idStr,
            ModelMap modelMap) {

        String regex = "\\d+";
        if (idStr != null && idStr.matches(regex)) {
            Integer id = Integer.parseInt(idStr);
            Question question = questionRepository.findById(id);
            modelMap.addAttribute("id", idStr);

            if (question != null) {
                modelMap.addAttribute("question", question);
                if (question.getAnswer() != null) {
                    modelMap.addAttribute("answer_description", question.getAnswer().getDescription());
                }

                return "question";
            }
        }
        return "redirect:/topics";
    }

    @GetMapping("/questions/{id}/edit")
    public String editAnswer(
            @PathVariable("id") String idStr,
            ModelMap modelMap) {

        String regex = "\\d+";
        if (idStr != null && idStr.matches(regex)) {
            Integer id = Integer.parseInt(idStr);
            Question question = questionRepository.findById(id);
            modelMap.addAttribute("id", idStr);

            if (question != null) {
                modelMap.addAttribute("question", question);
                if (question.getAnswer() != null) {
                    modelMap.addAttribute("answer_description", question.getAnswer().getDescription());
                }

                return "edit_question";
            }
        }
        return "redirect:/topics";
    }

}