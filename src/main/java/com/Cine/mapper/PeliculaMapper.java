package com.Cine.mapper;

import com.Cine.dto.PeliculaDTO;
import com.Cine.models.Pelicula;
import com.Cine.dto.PeliculaRegistroDTO;
import com.Cine.models.Idioma;
import com.Cine.models.ClasificacionRTC;

public class PeliculaMapper {

    public static PeliculaDTO aDTO(Pelicula entidad){
        return new PeliculaDTO(
                entidad.getIdpelicula(),
                entidad.getNombre(),
                entidad.getTiempo(),
                entidad.getSinopsis(),
                entidad.getImagenURL(),
                entidad.getIdClasificacionRTC().getIdClasificacionRTC(),
                entidad.getIdIdioma().getIdIdioma()
        );
    }

    //Para el administrador cuando registra una pelicula
    public static Pelicula aEntidad(PeliculaRegistroDTO dto, ClasificacionRTC clasificacion, Idioma idioma){
        Pelicula pelicula = new Pelicula();
        pelicula.setNombre(dto.nombrePelicula());
        pelicula.setTiempo(dto.tiempo());
        pelicula.setSinopsis(dto.sinopsis());
        pelicula.setImagenURL(dto.imagenURL());
        pelicula.setIdClasificacionRTC(clasificacion);
        pelicula.setIdIdioma(idioma);
        return pelicula;
    }
}
