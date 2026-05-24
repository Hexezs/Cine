package com.Cine.mapper;

import com.Cine.dto.CarteleraDTO;
import com.Cine.dto.CarteleraRegistroDTO;
import com.Cine.models.Cartelera;
import com.Cine.models.Pelicula;
import com.Cine.models.Sala;

public class CarteleraMapper {

    // ENTITY -> DTO
    public static CarteleraDTO aDTO(Cartelera entidad) {
        return new CarteleraDTO(
                entidad.getIdCartelera(),
                entidad.getFecha(),
                entidad.getHora(),
                entidad.getIdpelicula().getIdpelicula(),
                entidad.getIdsala().getIdsala());
    }

    // DTO -> ENTITY
    public static Cartelera aEntidad(CarteleraRegistroDTO dto, Pelicula pelicula, Sala sala) {
        Cartelera entidad = new Cartelera();
        entidad.setFecha(dto.fecha());
        entidad.setHora(dto.hora());
        entidad.setIdpelicula(pelicula);
        entidad.setIdsala(sala);
        return entidad;
    }
}