package com.example.OnlinePoll.service;


import com.example.OnlinePoll.model.OptionVote;
import com.example.OnlinePoll.model.Poll;
import com.example.OnlinePoll.repository.PollRepository;
import com.example.OnlinePoll.request.Vote;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class PollService {
    private PollRepository pollRepository;

    public PollService(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    public Poll createPoll(Poll poll) {
        return pollRepository.save(poll);
    }
    public List<Poll> getAllPolls(){
        return pollRepository.findAll();
    }

    public ResponseEntity<Poll> getPoll(long id) {
        return  pollRepository.findById(id).
                map(ResponseEntity::ok).
                orElse(ResponseEntity.notFound().build());
    }

    //vote logic
    public void  vote(long pollId,int optionIndex){
        //Get all poll from DB
        Poll poll=pollRepository.findById(pollId).orElseThrow(()->new RuntimeException("poll not found"));
        //get All options
        List<OptionVote> options=poll.getOptions();
        //index vote not valid
        if(optionIndex<0||optionIndex>=options.size()){
            throw  new IllegalArgumentException("invalid Option Index");
        }
        //get selected Option
        OptionVote selectedOption=options.get(optionIndex);
        selectedOption.setVoteCount(selectedOption.getVoteCount()+1);
        //save the vote
        pollRepository.save(poll);

    }

}
