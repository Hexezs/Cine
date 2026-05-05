package com.Cine.dto;

import java.time.LocalDate;
import java.util.List;

public record ReservaRegistroDTO (Integer idUsuario,
                                  Integer idSala,
                                  LocalDate fecha,
                                  List<BoletoDTO> boletos){
}
