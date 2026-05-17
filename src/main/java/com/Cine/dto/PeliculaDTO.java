package com.Cine.dto;

public record PeliculaDTO (int idPelicula,
                           String nombrePelicula,
                           int tiempo,
                           String sinopsis,
                           String imagenURL,
                           int idClasificacionRTC,
                           int idIdioma) {
}
