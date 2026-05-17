package com.Cine.mapper;

import com.Cine.dto.UsuarioRegistroDTO;
import com.Cine.models.TipoUsuario;
import com.Cine.models.Usuario;

public class UsuarioRegistroMapper {

    public static Usuario aEntidad(UsuarioRegistroDTO dto, TipoUsuario tipoUsuario){
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.nombre());
        usuario.setApellidoM(dto.apellidoM());
        usuario.setApellidoP(dto.apellidoP());
        usuario.setCorreo(dto.correo());
        usuario.setPassword(dto.password());
        usuario.setTipoUsuario(tipoUsuario);
        return usuario;
    }
}
