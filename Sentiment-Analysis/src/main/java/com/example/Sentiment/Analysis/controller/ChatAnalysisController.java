package com.example.Sentiment.Analysis.controller;

/*
import com.example.Sentiment.Analysis.model.SentimentModel;
import com.example.Sentiment.Analysis.util.WhatsAppParser;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping("/api/chat")
public class ChatAnalysisController {

    public ChatAnalysisController() {
        SentimentModel.train();
    }

    @PostMapping("/analyze")
    public Map<String, Object> analyzeChat(@RequestParam("file") MultipartFile file) throws Exception {
        // Save uploaded file
        String path = "chat.txt";
        file.transferTo(new java.io.File(path));

        List<String> messages = WhatsAppParser.parseChat(path);

        int positive = 0, negative = 0;
        for (String msg : messages) {
            String sentiment = SentimentModel.predict(msg);
            if (sentiment.equals("Positive")) positive++;
            else negative++;
        }

        Map<String, Object> result = new HashMap<>();
        result.put("totalMessages", messages.size());
        result.put("positive", positive);
        result.put("negative", negative);

        return result;
    }
}
*/

import com.example.Sentiment.Analysis.model.ChatResult;
import com.example.Sentiment.Analysis.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/chat")
public class ChatAnalysisController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/analyze")
    public ResponseEntity<?> analyzeChat(@RequestParam("file") MultipartFile file) {
        // Check if file is present
        if (file == null || file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("File is missing. Please upload a WhatsApp chat .txt file.");
        }

        try {
            // Normalize filename and save temporarily
            String fileName = file.getOriginalFilename().replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
            String path = System.getProperty("java.io.tmpdir") + "/" + fileName;
            file.transferTo(new java.io.File(path));

            System.out.println("Uploaded file saved at: " + path);

            // Call ChatService for sentiment analysis
            ChatResult result = chatService.analyzeChat(path);

            return ResponseEntity.ok(result);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing the file: " + e.getMessage());
        }
    }
}
