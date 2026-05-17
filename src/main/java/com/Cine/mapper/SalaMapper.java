package com.Cine.mapper;

import com.Cine.dto.SalaDTO;
import com.Cine.models.Sala;
import java.time.LocalDate;

public class SalaMapper {

    public static SalaDTO aDTO(Sala entidad){
        return new SalaDTO(
                entidad.getCapacidad(),
                entidad.getIdTipoSala() != null ? entidad.getIdTipoSala().getIdTipoSala() : 0,
                entidad.getNombreTipoSala()
        );
    }

    public static Sala aEntidad(SalaDTO dto){
        Sala entidad = new Sala();
        entidad.setCapacidad(dto.capacidad());
        return entidad;
    }
}
