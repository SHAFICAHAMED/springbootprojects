package com.example.Sentiment.Analysis.service;




import org.springframework.stereotype.Service;

@Service
public class SentimentService {

    public SentimentService() {
        // Train model at startup
        com.example.sentiment.analysis.model.SentimentModel.train();
    }

    public String analyzeMessage(String message) {
        return com.example.sentiment.analysis.model.SentimentModel.predict(message);
    }
}

