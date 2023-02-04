package com.example.webhub.repository;

import com.example.webhub.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Questions, Long> {

}
