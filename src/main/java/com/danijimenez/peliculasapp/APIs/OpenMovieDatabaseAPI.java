package com.danijimenez.peliculasapp.APIs;

import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class OpenMovieDatabaseAPI {

    private static String urlBase = "http://www.omdbapi.com/";
    private static String apiKey = "c397cdb2";

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
        return UriComponentsBuilder.fromHttpUrl("http://www.omdbapi.com/")
                .queryParam("apikey", apiKey).toUriString();
    }

    public String obtenerConsultaPeliculaporNombre(String nombre){
        return UriComponentsBuilder.fromHttpUrl(urlBase)
                .queryParam("apikey", apiKey)
                .queryParam("s",nombre).toUriString();
    }


}
