package com.danijimenez.peliculasapp.Services;

import com.danijimenez.peliculasapp.Entities.Pelicula;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PeliculasServices {


    public PeliculasServices() {}

    public List<Pelicula> convertirBusquedaPeliculaAPelicula(ResponseEntity<String> response) throws JsonProcessingException {
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
        for (JsonNode p : peliculasArray) {
            peliculas.add(new Pelicula(
                    p.get("imdbID").asText(),
                    p.get("Title").asText(),
                    p.get("Year").asInt(),
                    p.get("Poster").asText()
            ));
        }
        return peliculas;

    }
}
