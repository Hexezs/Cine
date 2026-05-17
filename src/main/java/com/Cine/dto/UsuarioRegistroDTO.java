package com.Cine.dto;

import com.Cine.models.TipoUsuario;

//registro de usuario
public record UsuarioRegistroDTO (String nombre,
                                  String apellidoM,
                                  String apellidoP,
                                  String correo,
                                  String password) {
}
