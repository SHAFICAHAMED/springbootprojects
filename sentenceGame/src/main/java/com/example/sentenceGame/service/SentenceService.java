package com.example.sentenceGame.service;


import com.example.sentenceGame.model.Sentence;
import com.example.sentenceGame.repo.SentenceRepository;
import org.apache.coyote.Response;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class SentenceService {
    private SentenceRepository sentenceRepository;

    public SentenceService(SentenceRepository sentenceRepository) {
        this.sentenceRepository = sentenceRepository;
    }

    public Sentence addSentence(Sentence sentence){
        return sentenceRepository.save(sentence);
    }

    public ResponseEntity<Sentence> getAllSentenceRandom(){
        List<Sentence> sentences=sentenceRepository.findAll();
        System.out.println("fetch from db");
        if(sentences.isEmpty())
              return  ResponseEntity.notFound().build();
        Sentence randomSentence=sentences.get(new Random().nextInt(sentences.size()));
        return  ResponseEntity.ok(randomSentence);
    }


    public ResponseEntity<Boolean> checkAnswer(Map<String,String> request)
    {
        Long sentenceId=Long.parseLong(request.get("id"));
        String userAnswer=request.get("answer").toLowerCase();

        Sentence sentence=sentenceRepository.findById(sentenceId).orElse(null);
        if(sentence==null)
             return ResponseEntity.notFound().build();
        boolean isCorrect=sentence.getAnswer().equals(userAnswer);
        return  ResponseEntity.ok(isCorrect);
    }

}
