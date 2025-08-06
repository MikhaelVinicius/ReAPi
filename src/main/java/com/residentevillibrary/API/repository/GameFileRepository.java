package com.residentevillibrary.API.repository;

import com.residentevillibrary.API.entity.GameFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GameFileRepository extends JpaRepository<GameFile, Long> {
    
    // Método para encontrar todos os arquivos de um jogo específico
    List<GameFile> findByGameId(Long gameId);
}