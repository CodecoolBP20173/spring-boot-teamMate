package com.codecool.teammate.controller;

import com.codecool.teammate.model.Answer;
import com.codecool.teammate.model.Question;
import com.codecool.teammate.model.Vote;
import com.codecool.teammate.model.VoteType;
import com.codecool.teammate.repository.QuestionRepository;
import com.codecool.teammate.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class VoteController {
    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private QuestionRepository questionRepository;


    @GetMapping("/questions/{id}/vote/{type}")
    public String saveVote(
            @PathVariable("id") String idStr,
            @PathVariable("type") String typeStr,
            ModelMap modelMap) {

        String regex = "\\d+";
        if (idStr != null && idStr.matches(regex)) {
            Integer id = Integer.parseInt(idStr);
            Question question = questionRepository.findById(id);
            modelMap.addAttribute("id", idStr);

            if (question != null) {
                Answer answer = question.getAnswer();

                if (answer != null) {
                    Vote vote = new Vote(answer);
                    if (typeStr.toUpperCase().equals(VoteType.UP.name())) {
                        vote.setVoteType(VoteType.UP);
                    } else if (typeStr.toUpperCase().equals(VoteType.DOWN.name())) {
                        vote.setVoteType(VoteType.DOWN);
                    }
                    return "redirect:/questions/{id}";
                }
            }
        }
        return "redirect:/topics";
    }
}
