
package com.Cine.services;

import com.Cine.dto.UsuarioInicioDTO;
import com.Cine.dto.UsuarioRegistroDTO;
import com.Cine.mapper.UsuarioMapper;
import com.Cine.mapper.UsuarioRegistroMapper;
import com.Cine.models.TipoUsuario;
import com.Cine.models.Usuario;
import com.Cine.repository.TipoUsuarioRepository;
import com.Cine.repository.UsuarioRepository;
import java.util.Optional;

public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService() {
        this.usuarioRepository = new UsuarioRepository();
    }

    // REGISTRO
    public Usuario registrarNuevoUsuario(UsuarioRegistroDTO datos) {

        if (usuarioRepository.existeCorreo(datos.correo())) {
            throw new RuntimeException("El correo ya está registrado.");
        }
        TipoUsuarioRepository TipoUsuarioRepository =
                new TipoUsuarioRepository();
        TipoUsuario tipo = TipoUsuarioRepository.getTipoByID(2);

        Usuario nuevo = UsuarioRegistroMapper.aEntidad(datos, tipo);

        usuarioRepository.addUser(nuevo);

        return nuevo;
    }

    // LOGIN
    public Optional<Usuario> iniciarSesion(UsuarioInicioDTO datos) {
        Usuario usuarioEncontrado = usuarioRepository.login(datos.correo(), datos.password());

        if (usuarioEncontrado != null &&
                usuarioEncontrado.getTipoUsuario() != null) {

            String rolDB =
                    usuarioEncontrado.getTipoUsuario().getNombreTipoUsuario();

            if (datos.rol().equalsIgnoreCase(rolDB)) {
                return Optional.of(usuarioEncontrado);
            }
        }
        return Optional.empty();
    }
    public void eliminarUsuario(Usuario usuario) {
        usuarioRepository.removeUser(usuario);
    }
    public void actualizarPerfil(Usuario usuario) {
        usuarioRepository.updateUser(usuario);
    }
}