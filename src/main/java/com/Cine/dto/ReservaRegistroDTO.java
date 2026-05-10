package com.Cine.dto;

import java.time.LocalDate;
import java.util.List;

//
public record ReservaRegistroDTO (int idUsuario,
                                  int idCartelera,
                                  //int idAsiento (lo mismo que abajo, pero mejor)
                                  List<Integer> idAsientoCompra
                                  ){
}
