package com.Cine.mapper;

import com.Cine.dto.SalaDTO;
import com.Cine.models.Sala;
import java.time.LocalDate;

public class SalaMapper {

    public static SalaDTO aDTO(Sala sala){
        return new SalaDTO(
                sala.getCapacidad(),
                LocalDate.parse(sala.getHorario()),
                sala.getDisponibles()
        );
    }

    public static Sala aEntidad(SalaDTO dto){
        Sala sala = new Sala();
        sala.setCapacidad(dto.capacidad());
        sala.setHorario(dto.horario().toString());
        sala.setDisponibles(dto.disponibles());
        // las FKs (idPelicula, idTipoSala, idAsiento)
        // se asignan en el Service después de buscarlas en BD
        return sala;
    }
}
