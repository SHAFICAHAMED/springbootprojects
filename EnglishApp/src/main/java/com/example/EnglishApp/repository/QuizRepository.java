package com.example.EnglishApp.repository;

import com.example.EnglishApp.model.QuizQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<QuizQuestion,Long> {


}
