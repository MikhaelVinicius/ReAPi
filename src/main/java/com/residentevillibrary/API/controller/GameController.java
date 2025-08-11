package com.residentevillibrary.API.controller;

import com.residentevillibrary.API.entity.Game;
import com.residentevillibrary.API.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/games")
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    // Endpoint principal para listas e busca (continua o mesmo)
    @GetMapping
    public Page<Game> getAllGames(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        if (StringUtils.hasText(search)) {
            return gameRepository.findByNameContainingIgnoreCase(search, pageable);
        } else {
            return gameRepository.findAll(pageable);
        }
    }

    /**
     * 👇 CORREÇÃO AQUI
     * Torne a URL para busca por nome mais explícita.
     * O frontend já chama esta URL, então esta é a que precisamos manter.
     */
    @GetMapping("/{name}")
    public ResponseEntity<Game> getGameByName(@PathVariable String name) {
        String formattedName = name.replace("-", " ");
        return gameRepository.findFirstByNameIgnoreCase(formattedName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * 👇 E CORREÇÃO AQUI
     * Adicione um prefixo como "/id/" para o endpoint de busca por ID para diferenciá-lo.
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable Long id) {
        return gameRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}