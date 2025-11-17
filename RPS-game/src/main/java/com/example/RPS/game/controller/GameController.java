package com.example.RPS.game.controller;

import com.example.RPS.game.model.GameResult;
import com.example.RPS.game.model.Move;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GameController {

    private final Map<String, String> moves = new HashMap<>();

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/move")
    public void receiveMove(Move move) {
        moves.put(move.getPlayer(), move.getChoice());

        if (moves.size() == 2) {
            List<String> players = new ArrayList<>(moves.keySet());
            String p1 = players.get(0), p2 = players.get(1);
            String c1 = moves.get(p1), c2 = moves.get(p2);

            String result;
            if (c1.equals(c2)) result = "Draw!";
            else if ((c1.equals("rock") && c2.equals("scissors")) ||
                    (c1.equals("paper") && c2.equals("rock")) ||
                    (c1.equals("scissors") && c2.equals("paper")))
                result = p1 + " wins!";
            else result = p2 + " wins!";

            GameResult gameResult = new GameResult();
            gameResult.setResult(result);
            gameResult.setPlayer1Move(p1 + ": " + c1);
            gameResult.setPlayer2Move(p2 + ": " + c2);

            messagingTemplate.convertAndSend("/topic/result", gameResult);
            moves.clear();
        }
    }
}
