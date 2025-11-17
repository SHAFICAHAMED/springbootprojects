package com.example.sentenceGame.controller;

import com.example.sentenceGame.model.Sentence;
import com.example.sentenceGame.service.SentenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/sentences")
@CrossOrigin(origins = "http://localhost:4200")
public class SentenceController {
    private SentenceService sentenceService;

    public SentenceController(SentenceService sentenceService) {
        this.sentenceService = sentenceService;
    }

    @GetMapping("/random")
    public ResponseEntity<Sentence> getRandom(){
        return  sentenceService.getAllSentenceRandom();
    }

    @PostMapping("/check")
    public  ResponseEntity<Boolean> checktheanswer(@RequestBody Map<String,String> request){
        return  sentenceService.checkAnswer(request);

    }
    @PostMapping
    public  Sentence addSentence(@RequestBody Sentence sentence)
    {
        return sentenceService.addSentence(sentence);
    }

}
