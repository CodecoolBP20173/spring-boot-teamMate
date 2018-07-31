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
public class AnswerController {

    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;

    @PostMapping("/questions")
    public String saveAnswer
            (@RequestParam("answer_input") String answerInput,
             @RequestParam("question_id") String idStr,
             RedirectAttributes redirectAttributes) {

        String regex = "\\d+";
        if (idStr != null && idStr.matches(regex)) {
            int question_id = Integer.parseInt(idStr);
            Question question = questionRepository.findById(question_id);
            
            if (question.getAnswer()== null) {
                answerRepository.save(Answer.create(answerInput,question));
            } else {
                Answer answer = question.getAnswer();
                answer.setDescription(answerInput);
                answerRepository.save(answer);
            }

            redirectAttributes.addAttribute("id", idStr);
        }
        return "redirect:/questions/{id}";
    }
}
