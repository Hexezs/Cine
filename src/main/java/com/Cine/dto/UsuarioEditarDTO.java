package com.Cine.dto;

public record UsuarioEditarDTO (
        int idUsuario,
        String nombre,
        String apellidoP,
        String apellidoM,
        String correo,
        String password
){
}
