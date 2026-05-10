package com.Cine.dto;

import java.time.LocalDate;
//historial cliente
public record ReservaDTO (int idReserva,
                          LocalDate fecha,
                          int idUsuario,
                          int idCartelera) {
}
