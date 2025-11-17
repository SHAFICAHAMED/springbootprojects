package com.example.EnglishApp.controller;

import com.example.EnglishApp.model.QuizQuestion;
import com.example.EnglishApp.repository.QuizRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz")
@CrossOrigin("*")
public class QuizController {

    private final QuizRepository quizRepository;

    public QuizController(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @GetMapping("/questions")
    public List<QuizQuestion> getQuizQuestions() {
        return quizRepository.findAll();
    }

    @PostMapping("/add")
    public QuizQuestion addQuestion(@RequestBody QuizQuestion question) {
        return quizRepository.save(question);
    }
}