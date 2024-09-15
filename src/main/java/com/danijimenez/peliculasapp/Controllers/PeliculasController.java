package com.danijimenez.peliculasapp.Controllers;

import com.danijimenez.peliculasapp.APIs.OpenMovieDatabaseAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/pelicula")
public class PeliculasController {

    @Autowired
    private OpenMovieDatabaseAPI openMovieDatabaseAPI;
    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/{peliculaNombre}")
    public ResponseEntity<String> buscarPeliculaPorNombre(@PathVariable String peliculaNombre){
        String url = openMovieDatabaseAPI.obtenerConsultaPeliculaporNombre(peliculaNombre);

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        return response;
    };

}
