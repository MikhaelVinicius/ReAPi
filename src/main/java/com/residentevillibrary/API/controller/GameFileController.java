package com.residentevillibrary.API.controller;

import com.residentevillibrary.API.entity.GameFile;
import com.residentevillibrary.API.service.GameFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/files")
public class GameFileController {

    @Autowired
    private GameFileService gameFileService;

    @PostMapping("/game/{gameId}")
    public ResponseEntity<GameFile> createFile(@RequestBody GameFile gameFile, @PathVariable Long gameId) {
        GameFile createdFile = gameFileService.createFile(gameFile, gameId);
        return new ResponseEntity<>(createdFile, HttpStatus.CREATED);
    }
   
    @GetMapping("/game/{gameId}")
    public ResponseEntity<List<GameFile>> getFilesByGameId(@PathVariable Long gameId) {
        List<GameFile> files = gameFileService.getFilesByGameId(gameId);
        return ResponseEntity.ok(files);
    }

    @PutMapping("/{fileId}")
    public ResponseEntity<GameFile> updateFile(@PathVariable Long fileId, @RequestBody GameFile gameFileDetails) {
        GameFile updatedFile = gameFileService.updateFile(fileId, gameFileDetails);
        return ResponseEntity.ok(updatedFile);
    }

    @DeleteMapping("/{fileId}")
    public ResponseEntity<Void> deleteFile(@PathVariable Long fileId) {
        gameFileService.deleteFile(fileId);
        return ResponseEntity.noContent().build(); 
    }
}