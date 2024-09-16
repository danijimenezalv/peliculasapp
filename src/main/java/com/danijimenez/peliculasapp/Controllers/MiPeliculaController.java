package com.danijimenez.peliculasapp.Controllers;

import com.danijimenez.peliculasapp.APIs.OpenMovieDatabaseAPI;
import com.danijimenez.peliculasapp.Entities.Pelicula;
import com.danijimenez.peliculasapp.Services.PeliculasServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/Mipelicula")
public class MiPeliculaController {

        @Autowired
        private OpenMovieDatabaseAPI openMovieDatabaseAPI;
        @Autowired
        private PeliculasServices peliculasServices;
        private RestTemplate restTemplate = new RestTemplate();

        @GetMapping("/{peliculaNombre}")
        public List<Pelicula> buscarPeliculaPorNombre(@PathVariable String peliculaNombre) throws JsonProcessingException {
            String url = openMovieDatabaseAPI.obtenerConsultaPeliculaporNombre(peliculaNombre);

            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            List<Pelicula> lPeliculas = peliculasServices.convertirBusquedaPeliculaAPelicula(response);

            return lPeliculas;
        };

        @GetMapping("/mostrar/{peliculaNombre}")
        public String mostrarPeliculaPorNombre(@PathVariable String peliculaNombre) throws JsonProcessingException {
            List<Pelicula> lPeliculas = this.buscarPeliculaPorNombre(peliculaNombre);

            StringBuilder res = new StringBuilder();
            for (Pelicula p : lPeliculas){
                res.append("<img src=" + p.getposterUrl() + "/>");
                res.append(p.toString()).append("<br>");
            }
            return res.toString();
        };
}
