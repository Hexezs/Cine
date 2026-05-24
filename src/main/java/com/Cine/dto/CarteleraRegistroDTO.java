package com.Cine.dto;

import java.time.LocalDate;

// DTO para registrar funciones/cartelera

public record CarteleraRegistroDTO(

        int idCartelera,
        int idPelicula,
        int idSala,
        LocalDate fecha,
        String hora

) {}