package com.residentevillibrary.API.entity;

public class Files {
    
    private String Name;
    private String texto;
    private String autor;
    private String data;

    public Files(String name, String texto, String autor, String data) {
        Name = name;
        this.texto = texto;
        this.autor = autor;
        this.data = data;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }



    

}
