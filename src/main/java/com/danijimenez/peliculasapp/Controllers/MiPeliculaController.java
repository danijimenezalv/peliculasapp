package com.danijimenez.peliculasapp.Controllers;

import com.danijimenez.peliculasapp.Entities.Pelicula;
import com.danijimenez.peliculasapp.Services.PeliculasServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/Mipelicula")
public class MiPeliculaController {

        @Autowired
        private PeliculasServices peliculasServices;


        @GetMapping("/{peliculaNombre}")
        public List<Pelicula> buscarPeliculaPorNombre(@PathVariable String peliculaNombre) throws JsonProcessingException {
            return peliculasServices.buscarPeliculasPorNombre(peliculaNombre);
        };

        @GetMapping("/mostrar/{peliculaNombre}")
        public String mostrarPeliculaPorNombre(@PathVariable String peliculaNombre,
                                               @RequestParam(required = false) boolean sorted) throws JsonProcessingException {

            List<Pelicula> lPeliculas = peliculasServices.buscarPeliculasPorNombre(peliculaNombre);

            if (lPeliculas == null || lPeliculas.isEmpty()){
                return "No se ha encontrando ninguna película con el título: " + peliculaNombre;
            }
            else {
                if(sorted){
                    peliculasServices.ordenarPeliculasPorAño(lPeliculas);
                }

                StringBuilder res = new StringBuilder();
                for (Pelicula p : lPeliculas){
                    res.append("<img src=").append(p.getposterUrl()).append("/>");
                    res.append(p.toString()).append("<br>");
                }
                return res.toString();
            }


        };
}
