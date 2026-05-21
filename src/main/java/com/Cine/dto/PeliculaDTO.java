package com.Cine.dto;

public record PeliculaDTO (int idpelicula,
                           String nombre,
                           int tiempo,
                           String sinopsis,
                           byte[] imagen,
                           int idClasificacionRTC,
                           int idIdioma) {
}
