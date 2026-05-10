package com.Cine.dto;

import java.time.LocalDate;
//ver cartelera
public record CarteleraDTO (int idCartelera,
                            LocalDate fecha,
                            String hora,
                            int idPelicula,
                            int idSala) {
}
