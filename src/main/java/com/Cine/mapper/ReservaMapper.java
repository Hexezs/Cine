package com.Cine.mapper;

import com.Cine.dto.ReservaRegistroDTO;
import com.Cine.models.Usuario;
import com.Cine.models.Reserva;

import java.time.LocalDate;

public class ReservaMapper {
    public static Reserva aEntidad(ReservaRegistroDTO dto, Usuario usuarioBD
                                   /*, Cartelera carteleraBD*/){
        Reserva nuevaReserva = new Reserva();

        nuevaReserva.setFecha(LocalDate.now());

        //asignar entidades que servicio ya busco en bd
        //nuevaReserva.setUsuario(usuarioBD);
        //nuevaReserva.setCartelera(carteleraBD);

        return nuevaReserva;
    }
}
