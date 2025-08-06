package com.residentevillibrary.API.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String releaseYear;
    private String platforms;
    
    @OneToMany(
            mappedBy = "game",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<GameFile> files = new ArrayList<>();

    // Construtor vazio (necessário para o JPA)
    public Game() {}

    // 👇 ESTE É O CONSTRUTOR QUE ESTAVA FALTANDO
    public Game(String name, String releaseYear, String platforms) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.platforms = platforms;
    }

    // Getters e Setters...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getReleaseYear() { return releaseYear; }
    public void setReleaseYear(String releaseYear) { this.releaseYear = releaseYear; }
    public String getPlatforms() { return platforms; }
    public void setPlatforms(String platforms) { this.platforms = platforms; }
    public List<GameFile> getFiles() { return files; }
    public void setFiles(List<GameFile> files) { this.files = files; }
}