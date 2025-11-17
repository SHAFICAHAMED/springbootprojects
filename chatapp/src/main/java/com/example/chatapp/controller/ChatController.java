//package com.example.chatapp.controller;
//
//
//import com.example.chatapp.model.ChatMessage;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class ChatController {
//
//    @MessageMapping("/message")        // /app/message
//    @SendTo("/topic/messages")        // /topic/messages
//    public ChatMessage send (ChatMessage message) {
//        return message;
//    }
//
//
//
//}


//modifications
package com.example.chatapp.controller;

import com.example.chatapp.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(ChatMessage message) {
        return message;
    }

    @MessageMapping("/typing")
    @SendTo("/topic/typing")
    public ChatMessage sendTyping(ChatMessage typingMsg) {
        System.out.println("Typing from: " + typingMsg.getSender());
        return typingMsg;
    }
}
