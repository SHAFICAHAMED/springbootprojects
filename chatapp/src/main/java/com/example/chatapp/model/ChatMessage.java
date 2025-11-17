//package com.example.chatapp.model;
//
//// ChatMessage.java
//public class ChatMessage {
//    private String sender;
//    private String content;
//
//    // Constructors
//    public ChatMessage() {}
//    public ChatMessage(String sender, String content) {
//        this.sender = sender;
//        this.content = content;
//    }
//
//    // Getters & Setters
//    public String getSender() { return sender; }
//    public void setSender(String sender) { this.sender = sender; }
//
//    public String getContent() { return content; }
//    public void setContent(String content) { this.content = content; }
//}

//modifications
package com.example.chatapp.model;

public class ChatMessage {
    private String sender;
    private String content;
    private String type;

    public ChatMessage() {}
    public ChatMessage(String sender, String content) {
        this.sender = sender;
        this.content = content;
    }


    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getSender() { return sender; }
    public void setSender(String sender) { this.sender = sender; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}

