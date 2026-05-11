package com.Cine.mapper;

import com.Cine.dto.IdiomaDTO;
import com.Cine.models.Idioma;

public class IdiomaMapper {

    public static IdiomaDTO aDTO(Idioma entidad){
        return new IdiomaDTO(
                entidad.getIdIdioma(),
                entidad.getNombreIdioma()
        );
    }

    public static Idioma aEntidad(IdiomaDTO dto){
        Idioma entidad = new Idioma();
        entidad.setNombreIdioma(dto.nombreIdioma());
        return entidad;
    }
}
