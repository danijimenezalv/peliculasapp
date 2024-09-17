package com.danijimenez.peliculasapp.APIs;

import com.danijimenez.peliculasapp.Entities.Pelicula;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
public class OpenMovieDatabaseAPI implements IMovieAPI {

    private final static String urlBase = "http://www.omdbapi.com/";
    private static String apiKey = "c397cdb2";
    private final static String parametroBuscarPeliculasPorNombre = "s";


    public OpenMovieDatabaseAPI() {}

    public static String geturlBase() {
        return urlBase;
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static void setApiKey(String apiKey) {
        OpenMovieDatabaseAPI.apiKey = apiKey;
    }

    public static String getParametroBuscarPeliculasPorNombre() {
        return parametroBuscarPeliculasPorNombre;
    }

    public String obtenerUrlConAPIkey(){
        return UriComponentsBuilder.fromHttpUrl(urlBase)
                .queryParam("apikey", apiKey).toUriString();
    }

    public String obtenerLlamadaHttpBuscarPeliculasPorNombre(String nombre){
        return UriComponentsBuilder.fromHttpUrl(urlBase)
                .queryParam("apikey", apiKey)
                .queryParam(parametroBuscarPeliculasPorNombre,nombre.trim().replace(" ","+")).toUriString();
    }

    public List<Pelicula> convertirRespuestaListaPeliculas(ResponseEntity<String> response)
            throws JsonProcessingException {

        String jsonString = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonString);
        JsonNode peliculasArray = rootNode.get("Search");

        List<Pelicula> peliculas = new ArrayList<>();
        if(peliculasArray == null || peliculasArray.isEmpty()){
            return peliculas;
        }
        else {
            for (JsonNode p : peliculasArray) {
                peliculas.add(new Pelicula(
                        p.get("imdbID").asText(),
                        p.get("Title").asText(),
                        p.get("Year").asInt(),
                        p.get("Poster").asText()
                ));
            }
        }
        return peliculas;

    }




}
