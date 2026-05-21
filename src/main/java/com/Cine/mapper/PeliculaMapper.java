package com.Cine.mapper;

import com.Cine.dto.PeliculaDTO;
import com.Cine.dto.PeliculaRegistroDTO;
import com.Cine.models.ClasificacionRTC;
import com.Cine.models.Idioma;
import com.Cine.models.Pelicula;

public class PeliculaMapper {

    public static PeliculaDTO aDTO(Pelicula entidad){

        return new PeliculaDTO(

                entidad.getIdpelicula(),
                entidad.getNombre(),
                entidad.getTiempo(),
                entidad.getSinopsis(),
                entidad.getImagen(),
                entidad.getIdClasificacionRTC().getIdClasificacionRTC(),
                entidad.getIdIdioma().getIdIdioma()

        );
    }

    // Para administrador cuando registra película
    public static Pelicula aEntidad(
            PeliculaRegistroDTO dto,
            ClasificacionRTC clasificacion,
            Idioma idioma
    ){

        Pelicula pelicula = new Pelicula();

        pelicula.setNombre(dto.nombrePelicula());

        pelicula.setTiempo(dto.tiempo());

        pelicula.setSinopsis(dto.sinopsis());

        pelicula.setImagen(dto.imagen());

        pelicula.setIdClasificacionRTC(clasificacion);

        pelicula.setIdIdioma(idioma);

        return pelicula;
    }
}