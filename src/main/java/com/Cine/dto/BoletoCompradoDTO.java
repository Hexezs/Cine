package com.Cine.dto;

import java.time.LocalDate;
import java.util.List;
//ultima ventana :D
public record BoletoCompradoDTO (String nombreCompletoUsuario,
                                 String nombrePelicula,
                                 LocalDate fecha,
                                 String hora,
                                 String nombreSala,
                                 List<String> asientos, //ej. C6
                                 int montoTotal){
}
