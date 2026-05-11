package com.Cine.mapper;

import com.Cine.dto.SalaDTO;
import com.Cine.models.Sala;
import java.time.LocalDate;

public class SalaMapper {

    public static SalaDTO aDTO(Sala entidad){
        return new SalaDTO(
                entidad.getCapacidad(),
                LocalDate.parse(entidad.getHorario()),
                entidad.getDisponibles()
        );
    }

    public static Sala aEntidad(SalaDTO dto){
        Sala entidad = new Sala();
        entidad.setCapacidad(dto.capacidad());
        entidad.setHorario(dto.horario().toString());
        entidad.setDisponibles(dto.disponibles());
        // las FKs (idPelicula, idTipoSala, idAsiento)
        // se asignan en el Service después de buscarlas en BD
        return entidad;
    }
}
