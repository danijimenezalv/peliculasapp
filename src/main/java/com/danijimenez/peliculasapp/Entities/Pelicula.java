package com.danijimenez.peliculasapp.Entities;

import org.springframework.web.multipart.MultipartFile;

public class Pelicula {

    private String imdbID;
    private String nombre;
    private int año;
    private String posterUrl;

    public Pelicula() {}

    public Pelicula(String imdbID, String nombre, int año, String posterUrl) {
        this.imdbID = imdbID;
        this.nombre = nombre;
        this.año = año;
        this.posterUrl = posterUrl;
    }

    public Pelicula(String imdbID, String nombre, int año) {
        this.imdbID = imdbID;
        this.nombre = nombre;
        this.año = año;
    }

    public String getImdbID() {return imdbID;}

    public void setImdbID(String imdbID) {this.imdbID = imdbID;}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public String getposterUrl() {
        return posterUrl;
    }

    public void setposterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    @Override
    public String toString() {
        return "Pelicula: " + nombre + " (" + año + ")";

    }
}
