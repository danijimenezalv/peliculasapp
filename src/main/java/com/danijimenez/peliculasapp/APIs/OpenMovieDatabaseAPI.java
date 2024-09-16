package com.danijimenez.peliculasapp.APIs;

import com.danijimenez.peliculasapp.Entities.Pelicula;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
public class OpenMovieDatabaseAPI implements IMovieAPI {

    private static String urlBase = "http://www.omdbapi.com/";
    private static String apiKey = "c397cdb2";
    private final static String parametroBuscarPeliculasPorNombre = "s";


    public OpenMovieDatabaseAPI() {}

    public static String geturlBase() {
        return urlBase;
    }

    public static void seturlBase(String urlBase) {
        OpenMovieDatabaseAPI.urlBase = urlBase;
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static void setApiKey(String apiKey) {
        OpenMovieDatabaseAPI.apiKey = apiKey;
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

        // 1. Obtener el cuerpo del ResponseEntity como String
        String jsonString = response.getBody();

        // 2. Crear un ObjectMapper para trabajar con el JSON
        ObjectMapper objectMapper = new ObjectMapper();

        // 3. Leer el JSON String en un JsonNode
        JsonNode rootNode = objectMapper.readTree(jsonString);

        // 4. Acceder al array de películas en el campo "Search"
        JsonNode peliculasArray = rootNode.get("Search");

        // 5. Crear una lista para almacenar los objetos Pelicula
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
