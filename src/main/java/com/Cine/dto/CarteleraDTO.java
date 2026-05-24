package com.Cine.dto;

import java.time.LocalDate;

public record CarteleraDTO(
        int idCartelera,
        LocalDate fecha,
        String hora,
        int idpelicula,
        int idsala

) {}