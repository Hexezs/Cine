package com.Cine.dto;

public record UsuarioDTO (int idUsuario,
                          String correo,
                          String nombre,
                          String apellidoP,
                          String apellidoM,
                          int idTipoUsuario) {
}
