package com.Cine.mapper;

import com.Cine.models.ClasificacionRTC;
import com.Cine.dto.ClasificaciónRTC_DTO;

public class ClasificacionRTCMapper {

    public static ClasificaciónRTC_DTO aDTO(ClasificacionRTC entidad){
        return new ClasificaciónRTC_DTO(
                entidad.getIdClasificacionRTC(),
                entidad.getNombre(),
                entidad.getDescripcion()
        );
    }

    public static ClasificacionRTC aEntidad(ClasificaciónRTC_DTO dto){
        ClasificacionRTC entidad = new ClasificacionRTC();
        entidad.setNombre(dto.nombreClasificacionRTC());
        entidad.setDescripcion(dto.descripcion());
        return entidad;
    }
}
