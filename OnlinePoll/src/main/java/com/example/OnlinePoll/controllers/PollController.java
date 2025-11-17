package com.example.OnlinePoll.controllers;


import com.example.OnlinePoll.model.Poll;
import com.example.OnlinePoll.request.Vote;
import com.example.OnlinePoll.service.PollService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/polls")
@CrossOrigin(origins = "https://angular-live-compiler-8kkq8qah.stackblitz.io")
public class PollController {
    private PollService pollService;


    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @PostMapping
    public Poll createPoll(@RequestBody  Poll poll){
        return  pollService.createPoll(poll);
    }
    @GetMapping
    public List<Poll> getAllPolls(){
        return pollService.getAllPolls();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Poll> getPoll(@PathVariable long id){
        return pollService.getPoll(id);
    }

    @PostMapping("/vote")
    public void votes(@RequestBody Vote vote){
        pollService.vote(vote.getPollId(), vote.getOptionIndex());
    }

}
