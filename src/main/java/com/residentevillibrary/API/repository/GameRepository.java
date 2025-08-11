package com.residentevillibrary.API.repository;

import com.residentevillibrary.API.entity.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional; 

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {


    Page<Game> findByNameContainingIgnoreCase(String name, Pageable pageable);
  
    Optional<Game> findFirstByNameIgnoreCase(String name);
}