package com.example.webhub.repository;

import com.example.webhub.entity.Subscribers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SubscribersRepository extends JpaRepository<Subscribers, UUID> {
    List<Subscribers> findByEmail(String email);
}
