package com.residentevillibrary.API.controller;

import com.residentevillibrary.API.entity.Files;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/files")
public class FileController {

   
    private final List<Files> allFiles = Arrays.asList(
        new Files("The Spencer Mansion Incident", "A report detailing the events...", "Chris Redfield", "1998-07-24"),
        new Files("Researcher's Letter", "Itchy. Tasty.", "Unnamed Researcher", "1998-05-21"),
        new Files("Leon's Report", "My first day as a cop was a nightmare.", "Leon S. Kennedy", "1998-09-29")
    );


    @GetMapping
    public ResponseEntity<List<Files>> getAllFiles() {
        return ResponseEntity.ok(allFiles);
    }


    @GetMapping("/{fileName}")
    public ResponseEntity<Files> getFileByName(@PathVariable String fileName) {
        return allFiles.stream()
                .filter(file -> file.getName().equalsIgnoreCase(fileName.replace("-", " ")))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}