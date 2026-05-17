package com.Cine.dto;
//el admin registra peliculas
public record PeliculaRegistroDTO (int idPelicula,
                                   String nombrePelicula,
                                   int tiempo,
                                   String sinopsis,
                                   String imagenURL,
                                   int idClasificacionRTC,
                                   int idIdioma) {
}
