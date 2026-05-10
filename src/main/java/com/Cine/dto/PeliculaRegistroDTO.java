package com.Cine.dto;
//el admin registra peliculas
public record PeliculaRegistroDTO (String nombrePelicula,
                                   int tiempo,
                                   String sinopsis,
                                   String imagenURL,
                                   int idClasificacionRTC,
                                   int idIdioma) {
}
