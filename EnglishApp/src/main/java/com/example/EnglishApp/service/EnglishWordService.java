package com.example.EnglishApp.service;

import com.example.EnglishApp.model.EnglishWord;
import com.example.EnglishApp.repository.EnglishWordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnglishWordService {
    private EnglishWordRepository englishWordRepository;

    public EnglishWordService(EnglishWordRepository englishWordRepository) {
        this.englishWordRepository = englishWordRepository;
    }
    public List<EnglishWord> getAllWords() {
        return englishWordRepository.findAll();
    }

    public EnglishWord addWord(EnglishWord word) {
        return englishWordRepository.save(word);
    }

    public void deleteWord(Long id) {
        englishWordRepository.deleteById(id);
    }
}
