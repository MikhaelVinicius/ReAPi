package com.residentevillibrary.API.service;

import com.residentevillibrary.API.entity.GameFile;
import java.util.List;

public interface GameFileService {
    GameFile createFile(GameFile gameFile, Long gameId);
    List<GameFile> getFilesByGameId(Long gameId);
    GameFile updateFile(Long fileId, GameFile gameFileDetails);
    void deleteFile(Long fileId);
}