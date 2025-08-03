package com.residentevillibrary.API.entity;

public class Game {

    private String name;
    private String year;
    private String[] platarfoms;
    private Files[] files;

    public Game(String name, String year, String[] platarfoms, Files[] files) {
        this.name = name;
        this.year = year;
        this.platarfoms = platarfoms;
        this.files = files;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String[] getPlatarfoms() {
        return platarfoms;
    }

    public void setPlatarfoms(String[] platarfoms) {
        this.platarfoms = platarfoms;
    }

    public Files[] getFiles() {
        return files;
    }

    public void setFiles(Files[] files) {
        this.files = files;
    }

    

    
    
}
