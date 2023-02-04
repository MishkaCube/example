package com.example.webhub.repository;

import com.example.webhub.entity.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectsRepository extends JpaRepository<Projects, Long> {
    List<Projects> findByTitleLike(String name);
}
