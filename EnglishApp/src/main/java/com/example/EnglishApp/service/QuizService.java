package com.example.EnglishApp.service;

import com.example.EnglishApp.model.EnglishWord;
import com.example.EnglishApp.model.QuizQuestion;
import com.example.EnglishApp.repository.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {
    private QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }
    public List<QuizQuestion> getAllWords() {
        return quizRepository.findAll();
    }

    public QuizQuestion addWord(QuizQuestion word) {
        return quizRepository.save(word);
    }

    public void deleteWord(Long id) {
        quizRepository.deleteById(id);
    }
}
