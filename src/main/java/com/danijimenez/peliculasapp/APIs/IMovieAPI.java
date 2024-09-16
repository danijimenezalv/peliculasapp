package com.danijimenez.peliculasapp.APIs;

import com.danijimenez.peliculasapp.Entities.Pelicula;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IMovieAPI {

    public String obtenerUrlConAPIkey();

    public String obtenerLlamadaHttpBuscarPeliculasPorNombre(String nombre);

    public List<Pelicula> convertirRespuestaListaPeliculas(ResponseEntity<String> response) throws JsonProcessingException;

}
