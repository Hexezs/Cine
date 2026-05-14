package com.Cine.dto;

import com.Cine.models.TipoUsuario;

//registro de usuario
public record UsuarioRegistroDTO (String nombre,
                                  String apellidoP,
                                  String apellidoM,
                                  String correo,
                                  String password) {
}
