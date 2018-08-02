package com.codecool.teammate.controller;


import com.codecool.teammate.model.*;
import com.codecool.teammate.repository.QuestionRepository;
import com.codecool.teammate.repository.ReviewRepository;
import com.codecool.teammate.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;


@Controller
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private VoteRepository voteRepository;

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
                if (answer != null) {
                    modelMap.addAttribute("answer", answer);

                    List<Vote> votes = voteRepository.findAllByAnswerId(answer.getId());

                    int upVotes = (int) votes
                            .stream()
                            .filter(vote -> vote.getVoteType().equals(VoteType.UP))
                            .count();
                    int downVotes = (int) votes
                            .stream()
                            .filter(vote -> vote.getVoteType().equals(VoteType.DOWN))
                            .count();

                    modelMap.addAttribute("up_votes", upVotes);
                    modelMap.addAttribute("down_votes", downVotes);

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