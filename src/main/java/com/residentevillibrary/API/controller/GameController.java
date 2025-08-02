package com.residentevillibrary.API.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/games")
public class GameController {

    @GetMapping
    public List<Map<String, Object>> getGames() {
     
        return Arrays.asList(
            Map.of("id", 1, "title", "Resident Evil 4 Remake"),
            Map.of("id", 2, "title", "Resident Evil Village"),
            Map.of("id", 3, "title", "Resident Evil 2 Remake")
        );
    }
}