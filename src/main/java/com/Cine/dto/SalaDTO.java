package com.Cine.dto;

import java.time.LocalDate;

public record SalaDTO (int capacidad,
                       LocalDate horario,
                       int disponibles){
}
