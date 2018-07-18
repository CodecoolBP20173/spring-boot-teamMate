package com.codecool.teammate.controller;


import com.codecool.teammate.model.Topic;
import com.codecool.teammate.repository.QuestionRepository;
import com.codecool.teammate.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


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

    @RequestMapping(value = "/topics", method = RequestMethod.GET)
    public String topics(
            @RequestParam(value = "id", required = false) String idStr,
            ModelMap modelMap) {

        if (idStr != null) {
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
