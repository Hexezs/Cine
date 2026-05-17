package com.Cine.mapper;

import com.Cine.dto.UsuarioDTO;
import com.Cine.models.Usuario;

public class UsuarioMapper {

    public static UsuarioDTO aDTO(Usuario entidad){
        return new UsuarioDTO(
                entidad.getIdusuario(),
                entidad.getCorreo(),
                entidad.getNombre(),
                entidad.getApellidoP(),
                entidad.getApellidoM(),
                entidad.getTipoUsuario().getIdTipoUsuario()
        );
    }
}
