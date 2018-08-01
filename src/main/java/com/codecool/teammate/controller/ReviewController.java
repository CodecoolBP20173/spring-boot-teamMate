package com.codecool.teammate.controller;


import com.codecool.teammate.model.Answer;
import com.codecool.teammate.model.Question;
import com.codecool.teammate.model.Review;
import com.codecool.teammate.repository.AnswerRepository;
import com.codecool.teammate.repository.QuestionRepository;
import com.codecool.teammate.repository.ReviewRepository;
import com.codecool.teammate.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;

    @PostMapping("/review")
    public String saveReview
            (@RequestParam("review_input") String reviewInput,
             @RequestParam("question_id") String idStr,
             RedirectAttributes redirectAttributes) {
        System.out.println(reviewInput);

        String regex = "\\d+";
        if (idStr != null && idStr.matches(regex)) {
            int question_id = Integer.parseInt(idStr);
            Question question = questionRepository.findById(question_id);
            Answer answer = answerRepository.findById(question.getAnswer().getId());

            if (answer.getReviews().isEmpty()) {
                reviewRepository.save(new Review(reviewInput, question, answer));
            } else {
                List<Review> reviews = answer.getReviews();
                Review review = new Review();
                review.setDescription(reviewInput);
                reviews.add(review);
                reviewRepository.save(review);
            }

            redirectAttributes.addAttribute("id", idStr);
        }
        return "redirect:/questions/{id}";
    }

        @GetMapping("/questions/{id}/review")
        public String editReview(
                @PathVariable("id") String idStr,
                ModelMap modelMap) {

            String regex = "\\d+";
            if (idStr != null && idStr.matches(regex)) {
                Integer id = Integer.parseInt(idStr);
                Question question = questionRepository.findById(id);
                List <Review> reviews = question.getAnswer().getReviews();
                modelMap.addAttribute("id", idStr);

                if (question != null) {
                    modelMap.addAttribute("question", question);
                    if (question.getAnswer() != null) {
                        modelMap.addAttribute("answer_description", question.getAnswer().getDescription());
                        if (!question.getAnswer().getReviews().isEmpty()) {
                            for (Review review : reviews) {
                                modelMap.addAttribute("review_description", review.getDescription());
                            }
                        }
                    }

                    return "review";
                }
            }
            return "redirect:/topics";
        }

    }

