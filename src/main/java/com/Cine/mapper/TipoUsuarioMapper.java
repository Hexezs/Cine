package com.Cine.mapper;

import com.Cine.dto.TipoUsuarioDTO;
import com.Cine.models.TipoUsuario;

public class TipoUsuarioMapper {

    public static TipoUsuarioDTO aDTO(TipoUsuario entidad){
        return new TipoUsuarioDTO(
        entidad.getIdTipoUsuario(),
        entidad.getNombreTipoUsuario()
        );
    }

    public static TipoUsuario aEntidad(TipoUsuarioDTO dto){
        TipoUsuario entidad = new TipoUsuario();
        entidad.setNombreTipoUsuario(dto.nombreTipoUsuario());
        return entidad;
    }
}
