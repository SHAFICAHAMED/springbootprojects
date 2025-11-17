package com.example.sentenceGame.repo;

import com.example.sentenceGame.model.Sentence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SentenceRepository extends JpaRepository<Sentence,Long> {
    Optional<Sentence> findById(long id);
}
