package com.codecool.teammate.controller;


import com.codecool.teammate.model.Answer;
import com.codecool.teammate.model.Question;
import com.codecool.teammate.model.Review;
import com.codecool.teammate.repository.QuestionRepository;
import com.codecool.teammate.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ReviewRepository reviewRepository;

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

                Answer answer = question.getAnswer();
                modelMap.addAttribute("answer", answer);

                if (answer != null) {
                    List<Review> reviews = reviewRepository.findAllByAnswerId(answer.getId());
                    modelMap.addAttribute("reviews", reviews);
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

                Answer answer = question.getAnswer();
                String answer_description = "";

                if (answer != null) {
                    answer_description = answer.getDescription();
                }
                modelMap.addAttribute("answer_description", answer_description);

                return "edit_answer";
            }
        }
        return "redirect:/topics";
    }

}