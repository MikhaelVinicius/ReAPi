package com.residentevillibrary.API.repository;

import com.residentevillibrary.API.entity.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    /**
     * O Spring Data JPA cria a consulta automaticamente a partir do nome do método.
     * "findByNameContainingIgnoreCase" se traduz em uma consulta SQL:
     * SELECT * FROM games WHERE LOWER(name) LIKE LOWER('%search_term%')
     *
     * @param name O termo de busca para o nome do jogo.
     * @param pageable O objeto que contém as informações de paginação (página, tamanho).
     * @return Uma página (Page) de jogos que correspondem à busca.
     */
    Page<Game> findByNameContainingIgnoreCase(String name, Pageable pageable);
}