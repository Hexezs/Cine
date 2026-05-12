package com.Cine.dto;

import com.Cine.models.Reserva;

public record BoletoDTO (int idBoleto,
                         int cantidad,
                         int monto,
                         Reserva idReserva,
                         String idAsiento){
}
