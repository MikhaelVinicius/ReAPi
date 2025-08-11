package com.residentevillibrary.API.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "game_files")
public class GameFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String fileType;

    @Column(length = 1000) 
    private String description;
    private String author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id", nullable = false) 
    @JsonBackReference 
    private Game game;

   

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }
    public String getFileType() { return fileType; }
    public void setFileType(String fileType) { this.fileType = fileType; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public Game getGame() { return game; }
    public void setGame(Game game) { this.game = game; }
}