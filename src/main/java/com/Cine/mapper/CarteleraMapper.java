package com.Cine.mapper;

import com.Cine.dto.CarteleraDTO;
import com.Cine.models.Cartelera;

public class CarteleraMapper {
    public static CarteleraDTO aDTO(Cartelera entidad){
        return new CarteleraDTO(
                entidad.getIdCartelera(),
                entidad.getFecha(),
                entidad.getHora(),
                entidad.getIdpelicula().getIdpelicula(),
                entidad.getIdsala().getIdsala()
        );
    }
    public static Cartelera aEntidad(CarteleraDTO dto){
        Cartelera entidad = new Cartelera();
        entidad.setFecha(dto.fecha());
        entidad.setHora(dto.hora());
        return entidad;
    }
}
