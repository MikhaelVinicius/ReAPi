package com.residentevillibrary.API.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // 👇 RENOMEADO AQUI
    private String releaseYear; 

    private String platforms;

    public Game() {}

    // 👇 E AQUI no construtor
    public Game(String name, String releaseYear, String platforms) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.platforms = platforms;
    }

    // GETTERS e SETTERS...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    // 👇 GETTER RENOMEADO
    public String getReleaseYear() { return releaseYear; }
    // 👇 SETTER RENOMEADO
    public void setReleaseYear(String releaseYear) { this.releaseYear = releaseYear; }

    public String getPlatforms() { return platforms; }
    public void setPlatforms(String platforms) { this.platforms = platforms; }
}