package com.Cine.dto;

public record PeliculaDTO (int idpelicula,
                           String nombre,
                           int tiempo,
                           String sinopsis,
                           String imagenURL,
                           int idClasificacionRTC,
                           int idIdioma) {
}
