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
public class TeamMateController {

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
                String word = searchWords[i].replaceAll("[^a-zA-Z0-9]","");

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

    @RequestMapping(value = "/topics", method = RequestMethod.GET)
    public String topics(
            @RequestParam(value = "id", required = false) String idStr,
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


    @GetMapping("/questions")
    public String question(
            @RequestParam("id") String idStr,
            ModelMap modelMap) {

        String regex = "\\d+";
        if (idStr != null && idStr.matches(regex)) {
            Integer id = Integer.parseInt(idStr);
            Question question = questionRepository.findById(id);
            modelMap.addAttribute("id", idStr);

            if (question != null) {
                modelMap.addAttribute("question_title", question.getTitle());
                modelMap.addAttribute("question_id", question.getId());
                if (question.getAnswer() != null){
                    modelMap.addAttribute("answer_description", question.getAnswer().getDescription());
                }

                return "edit_question";
            }
        }
        return "redirect:/topics";
    }

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
        return "redirect:/questions";
    }
}
