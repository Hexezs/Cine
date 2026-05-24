package com.Cine.mapper;

import com.Cine.dto.ReservaRegistroDTO;
import com.Cine.models.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservaMapper {

    public static Reserva aEntidad(
            ReservaRegistroDTO dto,
            Usuario usuarioBD,
            Cartelera carteleraBD,
            List<Boleto> boletos
    ) {
        Reserva reserva = new Reserva();
        reserva.setFecha(LocalDate.now());
        reserva.setIdusuario(usuarioBD);
        reserva.setIdcartelera(carteleraBD);
        for (Boleto b : boletos) {
            b.setIdReserva(reserva);
        }
        reserva.setBoletos(boletos);
        return reserva;
    }
}