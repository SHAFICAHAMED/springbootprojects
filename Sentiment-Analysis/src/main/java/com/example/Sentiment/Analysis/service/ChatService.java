package com.example.Sentiment.Analysis.service;


import com.example.Sentiment.Analysis.model.ChatResult;
import com.example.Sentiment.Analysis.util.WhatsAppParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    @Autowired
    private SentimentService sentimentService;

    public ChatResult analyzeChat(String filePath) throws Exception {
        List<String> messages = WhatsAppParser.parseChat(filePath);
        int positive = 0, negative = 0;

        for (String msg : messages) {
            String sentiment = sentimentService.analyzeMessage(msg);
            if ("Positive".equals(sentiment)) positive++;
            else negative++;
        }

        return new ChatResult(messages.size(), positive, negative);
    }
}
