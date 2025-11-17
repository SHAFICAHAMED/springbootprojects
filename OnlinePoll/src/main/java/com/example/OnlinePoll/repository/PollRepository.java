package com.example.OnlinePoll.repository;

import com.example.OnlinePoll.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollRepository extends JpaRepository<Poll,Long> {
}
