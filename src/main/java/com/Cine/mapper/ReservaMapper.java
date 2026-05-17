package com.Cine.mapper;

import com.Cine.dto.ReservaRegistroDTO;
import com.Cine.models.Cartelera;
import com.Cine.models.Usuario;
import com.Cine.models.Reserva;

import java.time.LocalDate;

public class ReservaMapper {
    public static Reserva aEntidad(ReservaRegistroDTO dto, Usuario usuarioBD, Cartelera carteleraBD){
        Reserva nuevaReserva = new Reserva();

        nuevaReserva.setFecha(LocalDate.now());

        //asignar entidades que servicio ya busco en bd
        nuevaReserva.setIdusuario(usuarioBD);
        nuevaReserva.setIdcartelera(carteleraBD);

        return nuevaReserva;
    }

}
