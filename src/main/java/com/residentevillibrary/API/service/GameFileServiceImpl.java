package com.residentevillibrary.API.service;

import com.residentevillibrary.API.entity.Game;
import com.residentevillibrary.API.entity.GameFile;
import com.residentevillibrary.API.repository.GameRepository;
import com.residentevillibrary.API.repository.GameFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException; // Importe esta exceção
import java.util.List;

@Service
public class GameFileServiceImpl implements GameFileService {

    @Autowired
    private GameFileRepository gameFileRepository;

    @Autowired
    private GameRepository gameRepository;

    @Override
    public GameFile createFile(GameFile gameFile, Long gameId) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new EntityNotFoundException("Game not found with id: " + gameId));

        gameFile.setGame(game);
        return gameFileRepository.save(gameFile);
    }

    @Override
    public List<GameFile> getFilesByGameId(Long gameId) {
        if (!gameRepository.existsById(gameId)) {
            throw new EntityNotFoundException("Game not found with id: " + gameId);
        }
        return gameFileRepository.findByGameId(gameId);
    }

    @Override
    public GameFile updateFile(Long fileId, GameFile gameFileDetails) {
        GameFile existingFile = gameFileRepository.findById(fileId)
                .orElseThrow(() -> new EntityNotFoundException("File not found with id: " + fileId));

        existingFile.setFileName(gameFileDetails.getFileName());
        existingFile.setFileType(gameFileDetails.getFileType());
        existingFile.setDescription(gameFileDetails.getDescription());
        existingFile.setAuthor(gameFileDetails.getAuthor());

        return gameFileRepository.save(existingFile);
    }

    @Override
    public void deleteFile(Long fileId) {
        if (!gameFileRepository.existsById(fileId)) {
            throw new EntityNotFoundException("File not found with id: " + fileId);
        }
        gameFileRepository.deleteById(fileId);
    }
}