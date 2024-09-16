package com.danijimenez.peliculasapp.Services;

import com.danijimenez.peliculasapp.APIs.OpenMovieDatabaseAPI;
import com.danijimenez.peliculasapp.Entities.Pelicula;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class PeliculasServices {

    @Autowired
    private OpenMovieDatabaseAPI movieAPI;
    private RestTemplate restTemplate = new RestTemplate();

    public PeliculasServices() {}


    public List<Pelicula> buscarPeliculasPorNombre(String nombre) throws JsonProcessingException {
        String url = movieAPI.obtenerLlamadaHttpBuscarPeliculasPorNombre(nombre);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        List<Pelicula> lPeliculas = movieAPI.convertirRespuestaListaPeliculas(response);
        return lPeliculas;
    }

    public void ordenarPeliculasPorAño(List<Pelicula> lPeliculas){
        lPeliculas.sort(Comparator.comparingInt(Pelicula::getAño).reversed());
    }


}
