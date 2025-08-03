package com.residentevillibrary.API.controller;

import com.residentevillibrary.API.entity.Game;
import com.residentevillibrary.API.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.StringUtils; // Importe esta classe

@RestController
@RequestMapping("/api/v1/games")
public class GameController {

    @Autowired // Injeta a dependência do nosso repositório
    private GameRepository gameRepository;

    /**
     * Endpoint ajustado para buscar jogos com paginação e busca opcional.
     * Rota: GET /api/v1/games?page=0&size=10&search=remake
     * * @param search O termo de busca opcional.
     * @param pageable O Spring criará este objeto a partir dos parâmetros 'page', 'size' e 'sort'.
     * @return Um objeto Page<Game> que o Spring converterá para o JSON esperado pelo frontend.
     */
    @GetMapping
    public Page<Game> getAllGames(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        
        // Verifica se o parâmetro de busca foi fornecido e não está vazio
        if (StringUtils.hasText(search)) {
            // Se houver busca, usa nosso método customizado do repositório
            return gameRepository.findByNameContainingIgnoreCase(search, pageable);
        } else {
            // Se não houver busca, retorna todos os jogos de forma paginada
            return gameRepository.findAll(pageable);
        }
    }

    /**
     * Endpoint para buscar um jogo específico pelo ID.
     * Rota: GET /api/v1/games/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable Long id) {
        return gameRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}