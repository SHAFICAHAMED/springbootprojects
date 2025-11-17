package com.example.Sentiment.Analysis.model;


public class ChatResult {
    private int totalMessages;
    private int positive;
    private int negative;

    public ChatResult() {}

    public ChatResult(int totalMessages, int positive, int negative) {
        this.totalMessages = totalMessages;
        this.positive = positive;
        this.negative = negative;
    }

    // Getters & Setters
    public int getTotalMessages() { return totalMessages; }
    public void setTotalMessages(int totalMessages) { this.totalMessages = totalMessages; }

    public int getPositive() { return positive; }
    public void setPositive(int positive) { this.positive = positive; }

    public int getNegative() { return negative; }
    public void setNegative(int negative) { this.negative = negative; }
}
