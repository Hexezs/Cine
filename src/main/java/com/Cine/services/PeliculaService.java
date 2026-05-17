package com.Cine.services;

import com.Cine.models.Pelicula;
import com.Cine.repository.PeliculaRepository;
import java.util.List;

public class PeliculaService {

    private final PeliculaRepository peliculaRepository =
            new PeliculaRepository();

    public void agregarPelicula(Pelicula pelicula){

        peliculaRepository.addPelicula(pelicula);
    }

    public List<Pelicula> obtenerPeliculas(){

        return peliculaRepository.getAllPeliculas();
    }
    public Pelicula buscarPorNombre(String nombre) {
        return peliculaRepository.getPeliculaByNombre(nombre);
    }
    public void eliminarPelicula(Pelicula pelicula) {
        peliculaRepository.removePelicula(pelicula);
    }
}