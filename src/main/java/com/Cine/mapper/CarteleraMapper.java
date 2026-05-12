package com.Cine.mapper;

import com.Cine.dto.CarteleraDTO;
import com.Cine.models.Cartelera;

public class CarteleraMapper {
    public static CarteleraDTO aDTO(Cartelera entidad){
        return new CarteleraDTO(
                entidad.getIdCartelera(),
                entidad.getFecha(),
                entidad.getIdPelicula(),
                entidad.getIdSala()
        );

        public static Cartelera aEntidad(CarteleraDTO dto){
            Cartelera entidad = new Idioma();
            entidad.setFecha(dto.fecha());
            return entidad;
        }
    }
}
