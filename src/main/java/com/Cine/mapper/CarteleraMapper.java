package com.Cine.mapper;

import com.Cine.dto.CarteleraDTO;
import com.Cine.models.Cartelera;

public class CarteleraMapper {
    public static CarteleraDTO aDTO(Cartelera entidad){
        return new CarteleraDTO(
                entidad.getIdCartelera(),
                entidad.getFecha(),
                entidad.getHora(),
                entidad.getIdpelicula() != null ? entidad.getIdpelicula().getIdPelicula() : 0,
                entidad.getIdsala() != null ? entidad.getIdsala().getIdsala() : 0
        );
    }
    public static Cartelera aEntidad(CarteleraDTO dto){
        Cartelera entidad = new Cartelera();
        entidad.setFecha(dto.fecha());
        entidad.setHora(dto.hora());
        return entidad;
    }
}
