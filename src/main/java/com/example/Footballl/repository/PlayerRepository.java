package com.example.Footballl.repository;

import com.example.Footballl.model.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    boolean existsByName(String name);
}

