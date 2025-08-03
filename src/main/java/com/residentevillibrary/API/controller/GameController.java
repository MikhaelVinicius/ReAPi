package com.residentevillibrary.API.controller;



import com.residentevillibrary.API.entity.Game;
import com.residentevillibrary.API.entity.Files;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/games")
public class GameController {

    private final List<Game> games = new ArrayList<>();


    public GameController() {
        Files[] re1Files = {
            new Files("The Spencer Mansion Incident", "A report detailing the events...", "Chris Redfield", "1998-07-24"),
            new Files("Researcher's Letter", "Itchy. Tasty.", "Unnamed Researcher", "1998-05-21")
        };
        games.add(new Game("Resident Evil 1", "1996", new String[]{"PlayStation", "PC"}, re1Files));

        Files[] re2Files = {
            new Files("Leon's Report", "My first day as a cop was a nightmare.", "Leon S. Kennedy", "1998-09-29")
        };
        games.add(new Game("Resident Evil 2", "1998", new String[]{"PlayStation", "PC", "N64"}, re2Files));
    }

   
    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        return ResponseEntity.ok(games);
    }

 
    @GetMapping("/{name}")
    public ResponseEntity<Game> getGameByName(@PathVariable String name) {
        
        return games.stream()
                .filter(game -> game.getName().equalsIgnoreCase(name.replace("-", " ")))
                .findFirst()
                .map(ResponseEntity::ok) 
                .orElse(ResponseEntity.notFound().build()); 
    }

    @GetMapping("/{name}/files")
    public ResponseEntity<Files[]> getFilesByGameName(@PathVariable String name) {
        return games.stream()
                .filter(game -> game.getName().equalsIgnoreCase(name.replace("-", " ")))
                .findFirst()
                .map(game -> ResponseEntity.ok(game.getFiles())) 
                .orElse(ResponseEntity.notFound().build());
    }
}