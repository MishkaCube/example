package com.example.webhub.repository;

import com.example.webhub.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findByTitleLike(String title);
    List<News> findByDate(LocalDate date);
}
