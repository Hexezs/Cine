package com.Cine.mapper;
import com.Cine.dto.UsuarioDTO;
import com.Cine.dto.UsuarioEditarDTO;
import com.Cine.models.Usuario;

public class UsuarioMapper {

    // ENTITY -> DTO
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

    // ACTUALIZAR ENTITY DESDE DTO
    public static void actualizarEntidad(Usuario usuario, UsuarioEditarDTO dto){
        usuario.setNombre(dto.nombre());
        usuario.setApellidoP(dto.apellidoP());
        usuario.setApellidoM(dto.apellidoM());
        usuario.setCorreo(dto.correo());
        usuario.setPassword(dto.password());
    }
}