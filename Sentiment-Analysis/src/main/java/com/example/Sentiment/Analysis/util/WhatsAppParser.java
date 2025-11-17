package com.example.Sentiment.Analysis.util;

import java.nio.file.*;
import java.util.*;

public class WhatsAppParser {
    public static List<String> parseChat(String filePath) throws Exception {
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        List<String> messages = new ArrayList<>();

        for (String line : lines) {
            if (line.contains("-")) {
                String[] parts = line.split("-", 2);
                if (parts.length > 1) {
                    String msg = parts[1].trim();
                    // Remove sender name
                    int idx = msg.indexOf(":");
                    if (idx != -1) msg = msg.substring(idx + 1).trim();
                    messages.add(msg);
                }
            }
        }
        return messages;
    }
}
