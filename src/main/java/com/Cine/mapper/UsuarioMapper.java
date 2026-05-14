//package com.Cine.mapper;
//
//import com.Cine.dto.UsuarioDTO;
//import com.Cine.models.Usuario;
//
//public class UsuarioMapper {
//
//    public static UsuarioDTO aDTO(Usuario entidad){
//        return new UsuarioDTO(
//                entidad.getIdusuario(),
//                entidad.getCorreo(),
//                entidad.getNombre(),
//                entidad.getApellidoP(),
//                entidad.getApellidoM(),
//                entidad.getTipoUsuario().getIdTipoUsuario()
//        );
//    }
//}
package com.Cine.mapper;

import com.Cine.dto.UsuarioRegistroDTO;
import com.Cine.models.TipoUsuario;
import com.Cine.models.Usuario;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioRegistroDTO DTO) {
        if (DTO == null) return null;
        Usuario usuario = new Usuario();
        usuario.setNombre(DTO.nombre());
        usuario.setApellidoP(DTO.apellidoP());
        usuario.setApellidoM("");
        usuario.setCorreo(DTO.correo());
        usuario.setPassword(DTO.password());
        return usuario;
    }
}