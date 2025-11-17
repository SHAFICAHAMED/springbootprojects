package com.example.EnglishApp.repository;

import com.example.EnglishApp.model.EnglishWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnglishWordRepository extends JpaRepository<EnglishWord,Long> {
    List<EnglishWord> findByCategory(String category);
}
