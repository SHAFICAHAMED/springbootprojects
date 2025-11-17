package com.example.EnglishApp.controller;


import com.example.EnglishApp.model.EnglishWord;
import com.example.EnglishApp.service.EnglishWordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/words")
@CrossOrigin("*") // Allow Angular to access this API
public class EnglishWordController {

    private EnglishWordService englishWordService;

    public EnglishWordController(EnglishWordService englishWordService) {
        this.englishWordService = englishWordService;
    }

    @GetMapping
    public List<EnglishWord> getAllWords() {
        return englishWordService.getAllWords();
    }

    @PostMapping
    public EnglishWord addWord(@RequestBody EnglishWord word) {
        return englishWordService.addWord(word);
    }

    @DeleteMapping("/{id}")
    public void deleteWord(@PathVariable Long id) {
        englishWordService.deleteWord(id);
    }
}
