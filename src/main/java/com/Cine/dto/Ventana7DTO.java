package com.Cine.dto;

import java.util.List;

// ver asientos de la ventana 7 de acuerdo a la sala
public record Ventana7DTO (int idCartelera,
                           String nombrePelicula,
                           String hora, //mostrar en rojo plis los asientos ocupados (si es posible)
                           List<Integer> idAsientosOcupados) {
}
