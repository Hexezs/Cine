package com.Cine.mapper;

import com.Cine.dto.TipoSalaDTO;
import com.Cine.models.TipoSala;

public class TipoSalaMapper {

    public static TipoSalaDTO aDTO(TipoSala entidad){
        return new TipoSalaDTO(
                entidad.getIdTipoSala(),
                entidad.getNombreTipoSala(),
                entidad.getDescripcion()
        );
    }

    public static TipoSala aEntidad(TipoSalaDTO dto){
        TipoSala entidad = new TipoSala();
        entidad.setNombreTipoSala(dto.nombreTipoSala());
        entidad.setDescripcion(dto.descripcion());
        return entidad;
    }
}
